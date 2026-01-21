package com.github.jvondoellinger.agp_protocol;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.github.jvondoellinger.agp_protocol")
public class ArchitectureTest {
	@ArchTest
	static final ArchRule shared_kernel_independent = null;
}
