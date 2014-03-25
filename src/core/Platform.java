package core;

public class Platform
{
    private static String OS = System.getProperty("os.name").toLowerCase();

    public enum Directory
    {
        DIRECTORY_DOCUMENTS,
        DIRECTORY_CACHE,
        DIRECTORY_APP,
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isAndroid() {
        return OS.contains("android") || OS.contains("linux"); // (linux on android simulator)
    }

    public static String getDirectoryPath(Directory directoryId)
    {
        if (directoryId == Directory.DIRECTORY_CACHE)
            return System.getProperty("java.io.tmpdir");
        else if (directoryId == Directory.DIRECTORY_APP)
            return System.getProperty("user.dir");
        else if (directoryId == Directory.DIRECTORY_DOCUMENTS) {
            if (isAndroid())
                return System.getProperty("java.io.tmpdir") + "/hqis/documents";
            else
                return System.getProperty("user.home") + "/hqis/documents";
        } else
            throw new Error("invalid directoryId");
    }

    public static String getFilePath(Directory directoryId, String fileName)
    {
        return getDirectoryPath(directoryId) + "/" + fileName;
    }
}
