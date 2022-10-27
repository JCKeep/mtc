package com.example.mtc.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {
  public static void main(String[] args) throws Exception {
    List<String> warnings = new ArrayList<>();
    boolean overwire = true;
    InputStream in = MybatisGenerator.class.getResourceAsStream("/GeneratorConfig.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(in);
    assert in != null;
    in.close();

    DefaultShellCallback callback = new DefaultShellCallback(overwire);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
    for (String warning : warnings) {
      System.out.println(warning);
    }
  }
}
