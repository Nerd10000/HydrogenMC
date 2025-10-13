package dragon.me.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class PerformanceLogger {

    private static final Path LOGS_FOLDER_PATH = Path.of("logs");
    private static final SystemInfo SYSTEM_INFO = new SystemInfo();
    private static final HardwareAbstractionLayer HAL = SYSTEM_INFO.getHardware();
    private static final OperatingSystem OS = SYSTEM_INFO.getOperatingSystem();

    public static void writeLog() throws IOException {
        if (!LOGS_FOLDER_PATH.toFile().exists()) {
            LOGS_FOLDER_PATH.toFile().mkdirs();
        }

        // Use a timestamped log filename
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        File file = new File(LOGS_FOLDER_PATH.toFile(), "performance-report_" + timestamp + ".log");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("---- [PERFORMANCE DETAILS] ----\n\n");

            // ==============================
            // System / OS Info
            // ==============================
            writer.write("[System Info]\n");
            writer.write(" OS: " + OS.toString() + "\n");
            writer.write(" Manufacturer: " + HAL.getComputerSystem().getManufacturer() + "\n");
            writer.write(" Model: " + HAL.getComputerSystem().getModel() + "\n");
            writer.write(" Serial Number: ********* " + "\n\n");

            // ==============================
            // CPU Info
            // ==============================
            CentralProcessor cpu = HAL.getProcessor();
            writer.write("[CPU]\n");
            writer.write(" Model: " + cpu.getProcessorIdentifier().getName() + "\n");
            writer.write(" Vendor: " + cpu.getProcessorIdentifier().getVendor() + "\n");
            writer.write(" Family: " + cpu.getProcessorIdentifier().getFamily() + "\n");
            writer.write(" Microarchitecture: " + cpu.getProcessorIdentifier().getMicroarchitecture() + "\n");
            writer.write(" Logical Cores: " + cpu.getLogicalProcessorCount() + "\n");
            writer.write(" Physical Cores: " + cpu.getPhysicalProcessorCount() + "\n");
            writer.write(" Max Frequency: " + (cpu.getMaxFreq() / 1_000_000) + " MHz\n\n");

            // ==============================
            // Memory Info
            // ==============================
            GlobalMemory memory = HAL.getMemory();
            writer.write("[Memory]\n");
            writer.write(String.format(" Total: %.2f GB\n", memory.getTotal() / (1024.0 * 1024 * 1024)));
            writer.write(String.format(" Available: %.2f GB\n", memory.getAvailable() / (1024.0 * 1024 * 1024)));
            List<oshi.hardware.PhysicalMemory> memModules = memory.getPhysicalMemory();
            if (!memModules.isEmpty()) {
                int i = 1;
                for (var mem : memModules) {
                    writer.write(" Module " + i++ + ": " + mem.getCapacity() / (1024.0 * 1024 * 1024) + " GB @ "
                            + (mem.getClockSpeed() / 1_000_000) + " MHz (" + mem.getManufacturer() + ")\n");
                }
            }
            writer.write("\n");

            // ==============================
            // Storage Info
            // ==============================
            writer.write("[Storage]\n");
            for (HWDiskStore disk : HAL.getDiskStores()) {
                writer.write(" Drive: " + disk.getName() + "\n");
                writer.write("  Model: " + disk.getModel() + "\n");
                writer.write("  Serial: *********"  + "\n");
                writer.write(String.format("  Size: %.2f GB\n\n", disk.getSize() / (1024.0 * 1024 * 1024)));
            }

            // ==============================
            // GPU Info
            // ==============================
            writer.write("[Graphics]\n");
            for (GraphicsCard gpu : HAL.getGraphicsCards()) {
                writer.write(" GPU: " + gpu.getName() + "\n");
                writer.write("  Vendor: " + gpu.getVendor() + "\n");
                writer.write("  VRAM: " + (gpu.getVRam() / (1024.0 * 1024 * 1024)) + " GB\n\n");
            }

            // ==============================
            // Motherboard Info
            // ==============================
            writer.write("[Motherboard]\n");
            ComputerSystem cs = HAL.getComputerSystem();
            writer.write(" Manufacturer: " + cs.getBaseboard().getManufacturer() + "\n");
            writer.write(" Model: " + cs.getBaseboard().getModel() + "\n");
            writer.write(" Version: " + cs.getBaseboard().getVersion() + "\n");
            writer.write(" Serial Number: *********" + "\n");

            writer.write("\n---- [END OF REPORT] ----\n");

        } catch (Exception e) {
            System.err.println("Error writing performance log: " + e.getMessage());
        }
    }
}