val runkapt: Boolean by extra {
    val runkapt: Boolean? = project.findProperty("runKapt")?.toString()?.toBoolean()
    runkapt ?: (providers.systemProperty("runKapt").forUseAtConfigurationTime().orNull)?.toString()?.toBoolean() ?: true
}

val generateFactories: Boolean by extra {
    val runkapt: Boolean? = project.findProperty("generateFactories")?.toString()?.toBoolean()
    runkapt ?: (providers.systemProperty("generateFactories").forUseAtConfigurationTime().orNull)?.toString()?.toBoolean() ?: false
}
