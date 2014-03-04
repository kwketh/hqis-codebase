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
        return OS.indexOf("win") >= 0;
    }

    public static boolean isMac() {
        return OS.indexOf("mac") >= 0;
    }

    public static boolean isAndroid() {
        return OS.indexOf("android") >= 0;
    }

    public static String getDirectoryPath(Directory directoryId)
    {
        if (directoryId == Directory.DIRECTORY_CACHE)
            return System.getProperty("java.io.tmpdir");
        else if (directoryId == Directory.DIRECTORY_APP)
            return System.getProperty("user.dir");
        else if (directoryId == Directory.DIRECTORY_DOCUMENTS)
            return System.getProperty("user.home") + "/hqis/documents";
        else
            throw new Error("invalid directoryId");
    }

    public static String getFilePath(Directory directoryId, String fileName)
    {
        return getDirectoryPath(directoryId) + "/" + fileName;
    }
}
