package utils;

public class ExecutionUtils {
    public static void stopExecution(String msg, Exception e) {
        Logger.log(LocUtils.msg(msg));

        if (e != null)
            e.printStackTrace();

        System.exit(1);
    }
}
