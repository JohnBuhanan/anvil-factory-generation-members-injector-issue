package example.gradle

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isFailure
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.provider.Provider
import org.junit.Test
import java.util.Optional

class VersionCatalogExtTest {

    @Test
    internal fun `when a dependency exists correct gradle Provider returned`() {

        val expectedDependency = "expected-dependency"
        val minimalExternalModuleDependencyProvider: Provider<MinimalExternalModuleDependency> =
            object : Provider<MinimalExternalModuleDependency> by stub() {}
        val versionCatalog = object : VersionCatalog by stub() {
            override fun findDependency(name: String): Optional<Provider<MinimalExternalModuleDependency>> =
                Optional.of(minimalExternalModuleDependencyProvider)
        }
        assertThat(versionCatalog.resolveDependency(expectedDependency)).isEqualTo(
            minimalExternalModuleDependencyProvider
        )
    }

    @Test
    internal fun `when a dependency does not exist throws exception with message`() {

        val expectedDependency = "expected-dependency"
        val versionCatalog = object : VersionCatalog by stub() {
            override fun findDependency(name: String): Optional<Provider<MinimalExternalModuleDependency>> =
                Optional.empty()
        }
        assertThat {
            versionCatalog.resolveDependency(expectedDependency)
        }.isFailure()
            .hasMessage("Couldn't resolve dependency $expectedDependency is it defined in the version catalog? (toml file)")
    }

    @Test
    internal fun `when a version exists correct gradle Provider returned`() {

        val expectedVersion = "expected-version"
        val versionConstraint = object : VersionConstraint by stub() {}
        val versionCatalog = object : VersionCatalog by stub() {
            override fun findVersion(name: String): Optional<VersionConstraint> = Optional.of(versionConstraint)
        }
        assertThat(versionCatalog.resolveVersion(expectedVersion)).isEqualTo(versionConstraint)
    }

    @Test
    internal fun `when a version does not exist throws exception with message`() {
        val expectedVersion = "expected-version"
        val versionCatalog = object : VersionCatalog by stub() {
            override fun findVersion(name: String): Optional<VersionConstraint> = Optional.empty()
        }
        assertThat {
            versionCatalog.resolveVersion(expectedVersion)
        }.isFailure()
            .hasMessage("Couldn't resolve version $expectedVersion is it defined in the version catalog? (toml file)")
    }

    @Test
    internal fun `when a bundle exists correct gradle Provider returned`() {
        val expectedBundle = "expected-bundle"
        val externalModuleDependencyBundle: Provider<ExternalModuleDependencyBundle> =
            object : Provider<ExternalModuleDependencyBundle> by stub() {}
        val versionCatalog = object : VersionCatalog by stub() {
            override fun findBundle(name: String): Optional<Provider<ExternalModuleDependencyBundle>> =
                Optional.of(externalModuleDependencyBundle)
        }
        assertThat(versionCatalog.resolveBundle(expectedBundle)).isEqualTo(externalModuleDependencyBundle)
    }

    @Test
    internal fun `when a bundle does not exist throws exception with message`() {
        val expectedBundle = "expected-bundle"
        val versionCatalog = object : VersionCatalog by stub() {
            override fun findBundle(name: String): Optional<Provider<ExternalModuleDependencyBundle>> =
                Optional.empty()
        }
        assertThat {
            versionCatalog.resolveBundle(expectedBundle)
        }.isFailure()
            .hasMessage("Couldn't resolve bundle $expectedBundle is it defined in the version catalog? (toml file)")
    }

    @Test
    internal fun `when gradle Project libs property is called and catalog named libs is present should return expected VersionCatalog`() {
        val expectedVersionCatalog = object : VersionCatalog by stub() {}
        val project = createGradleProjectWithVersionCatalog { name ->
            if (name == "libs") Optional.of(expectedVersionCatalog) else Optional.empty()
        }
        assertThat(project.libs).isEqualTo(expectedVersionCatalog)
    }

    @Test
    internal fun `when gradle Project libs property is called and no catalog named libs is present an exception is thrown`() {
        val project = createGradleProjectWithVersionCatalog { Optional.empty() }
        assertThat {
            project.libs
        }.isFailure().hasMessage("Catalog named libs doesn't exist")
    }

    private fun createGradleProjectWithVersionCatalog(versionCatalogFinder: (String?) -> Optional<VersionCatalog>) =
        object : Project by stub() {
            override fun getExtensions(): ExtensionContainer = object : ExtensionContainer by stub() {
                override fun <T : Any> getByType(type: Class<T>): T =
                    type.cast(object : VersionCatalogsExtension by stub() {
                        override fun find(name: String): Optional<VersionCatalog> = versionCatalogFinder(name)
                    })
            }
        }
}
