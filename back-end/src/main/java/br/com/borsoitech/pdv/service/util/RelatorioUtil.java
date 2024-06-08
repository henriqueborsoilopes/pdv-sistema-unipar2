package br.com.borsoitech.pdv.service.util;

import java.nio.file.Paths;

public class RelatorioUtil {

    public static String adjustPath(String jrxmlPath) {
        String basePath = Paths.get("src/main/java/br/com/borsoitech/pdv/service").toAbsolutePath().toString();
        String adjustedPath = Paths.get(basePath, jrxmlPath).toString();
        return adjustedPath.replace("\\", "/");
    }
}
