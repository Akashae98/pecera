package com.mycompany.animacionpecera;

import java.lang.management.ManagementFactory;

public class DebugUtil {
    public static boolean isDebugging() {
        return ManagementFactory.getRuntimeMXBean()
                .getInputArguments().toString().contains("-agentlib:jdwp");
    }
}
