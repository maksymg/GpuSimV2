package com.gpusim2.runtime;

import com.gpusim2.config.*;
import gridsim.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: maksym
 * Date: 10/20/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class GridSimRuntime {

    private static final double RESOURCES_PEAK_LOAD = 0.0;
    private static final double RESOURCES_OFFPEAK_LOAD = 0.0;
    private static final double RESOURCES_HOLIDAY_LOAD = 0.0;
    private static final long RESOURCES_CALENDAR_SEED = 11L*13*17*19*23+1;

    private static final LinkedList<Integer> RESOURCES_CALENDAR_HOLIDAYS;
    private static final LinkedList<Integer> RESOURCES_CALENDAR_WEEKENDS;
    static {
        // All resources will work all days in month work:

        RESOURCES_CALENDAR_HOLIDAYS = new LinkedList<Integer>();
        RESOURCES_CALENDAR_WEEKENDS = new LinkedList<Integer>();
        RESOURCES_CALENDAR_WEEKENDS.add(new Integer(Calendar.SATURDAY));
        RESOURCES_CALENDAR_WEEKENDS.add(new Integer(Calendar.SUNDAY));
    }

    private GridSimConfig config;
    private GridSimOutput output;

    private GridletsContainer gridletsContainer;
    private GpuSimUser gpuSimUser;

    private static GridSimRuntime instance;

    public static GridSimRuntime getInstance() {
        if (instance == null) {
            instance = new GridSimRuntime();
        }

        return instance;
    }

    public GridSimRuntime() {
        this.config = new GridSimConfig();
        this.output = new GridSimOutput();
    }

    public GridSimOutput getOutput() {
        return output;
    }

    public GridletsContainer getGridletsContainer() {
        return gridletsContainer;
    }

    public void loadConfig(String filePath) throws FileNotFoundException, IncompatibleVersionException {
        printRuntimeMessage("Loading configuration from file: " + filePath);

        GridSimConfig config;

        FileInputStream in = new FileInputStream(filePath);
        XMLDecoder xmlDecoder = new XMLDecoder(in);
        config = (GridSimConfig) xmlDecoder.readObject();
        xmlDecoder.close();

        if (config.getVersion() != GridSimConfig.CURRENT_CONFIG_VERSION) {
            throw new IncompatibleVersionException(GridSimConfig.CURRENT_CONFIG_VERSION, config.getVersion());
        }

        this.config = config;

        printRuntimeMessage("Configuration loaded");
    }

    public void saveOutput(String filePath) throws FileNotFoundException {
        printRuntimeMessage("Saving output to file: " + filePath);

        FileOutputStream out = new FileOutputStream(filePath);
        XMLEncoder xmlEncoder = new XMLEncoder(out);
        xmlEncoder.writeObject(output);
        xmlEncoder.flush();
        xmlEncoder.close();

        printRuntimeMessage("Output saved");
        System.out.println("Saved to " + filePath);
    }

    public static void createTestConfig(String filePath) throws FileNotFoundException {
        printRuntimeMessage("Creating and saving test configuration to file: " + filePath);

        // Machines list
        GridSimMachineConfig machine = new GridSimMachineConfig();
        machine.setPeCount(6);
        machine.setPeRating(3);
        machine.setCount(5);

        LinkedList<GridSimMachineConfig> machines = new LinkedList<GridSimMachineConfig>();
        machines.add(machine);

        // Resources list
        GridSimResourceConfig resource = new GridSimResourceConfig();
        resource.setArch("Test Architecture");
        resource.setOs("Test OS");
        resource.setBaudRate(100.0);
        resource.setCostPerSec(55.0);
        resource.setMachines(machines);
        resource.setCount(2);

        LinkedList<GridSimResourceConfig> resources = new LinkedList<GridSimResourceConfig>();
        resources.add(resource);

        // Gridlets list
        GridSimGridletConfig gridlet = new GridSimGridletConfig();
        gridlet.setLength(100.0);
        gridlet.setInputSize(50);
        gridlet.setOutputSize(30);
        gridlet.setCount(10);

        LinkedList<GridSimGridletConfig> gridlets = new LinkedList<GridSimGridletConfig>();
        gridlets.add(gridlet);

        // Total configuration
        GridSimConfig config = new GridSimConfig();
        config.setLinkBaudRate(560.0);
        config.setResources(resources);
        config.setGridlets(gridlets);

        // Save configuration to passed file path
        FileOutputStream out = new FileOutputStream(filePath);
        XMLEncoder xmlEncoder = new XMLEncoder(out);
        xmlEncoder.writeObject(config);
        xmlEncoder.flush();
        xmlEncoder.close();

        printRuntimeMessage("Test configuration created");
    }

    public void initGridSim() {
        // We have only one GridSim-derived entity, so only one user.
        int num_user = 1;

        Calendar calendar = Calendar.getInstance();

        // Mean trace GridSim events/activities
        boolean trace_flag = false;

        // List of files or processing names to be excluded from any statistical measures
        String[] exclude_from_file = {""};
        String[] exclude_from_processing = {""};

        // The name of a report file to be written.
        String report_name = null;

        // Initialize the GridSim package
        printRuntimeMessage("Initializing GridSim package");
        GridSim.init(num_user, calendar, trace_flag, exclude_from_file,
                exclude_from_processing, report_name);
        printRuntimeMessage("Initializing GridSim package initialized");
    }

    public void createResources() throws GridSimRuntimeException {
        printRuntimeMessage("Creating resources");

        int currentResourceID = 0;
        for (GridSimResourceConfig resConfig : config.getResources()) {
            MachineList machineList = new MachineList();
            int currentMachineID = 0;

            for (GridSimMachineConfig mc : resConfig.getMachines()) {
                machineList.add(new Machine(currentMachineID, mc.getPeCount(), mc.getPeRating()));
                currentMachineID++;
            }

            ResourceCharacteristics rc = new ResourceCharacteristics(resConfig.getArch(), resConfig.getOs(),
                    machineList, resConfig.getAllocPolicy(), resConfig.getTimeZone(), resConfig.getCostPerSec());

            ResourceCalendar calendar = new ResourceCalendar(resConfig.getTimeZone(), RESOURCES_PEAK_LOAD,
                    RESOURCES_OFFPEAK_LOAD, RESOURCES_HOLIDAY_LOAD, RESOURCES_CALENDAR_WEEKENDS,
                    RESOURCES_CALENDAR_HOLIDAYS, RESOURCES_CALENDAR_SEED);

            for (long i = 0; i < resConfig.getCount(); ++i) {
                GridResource resource;
                String resName = String.format("Resource_%1$d", currentResourceID);

                try {
                    printRuntimeMessage("Creating resource " + resName);
                    resource = new GridResource(resName, resConfig.getBaudRate(), rc, calendar);
                }
                catch (Exception e) {
                    String desc = "Failed to create resource " + resName + ": " + e.getMessage();
                    printRuntimeMessage(desc);
                    throw new GridSimRuntimeException(desc);
                }

                currentResourceID++;
            }
        }

        printRuntimeMessage("Resouces created");

    }


    public void createGridletsContainer()
    {
        printRuntimeMessage("Creating Gridlets Container");

        gridletsContainer = new GridletsContainer(config.getGridlets());

        printRuntimeMessage("Gridlets Container created");
    }

    public void createUser(GridSimRuntime gridSimRuntime) throws Exception {
        printRuntimeMessage("Creating user");

        gpuSimUser = new GpuSimUser(config.getLinkBaudRate(), gridletsContainer, gridSimRuntime);

        printRuntimeMessage("User created");
    }

    private static void printRuntimeMessage(String message) {
        System.out.println("[GridSimRuntime] " + message);
    }
}
