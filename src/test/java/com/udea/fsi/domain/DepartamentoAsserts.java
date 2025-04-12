package com.udea.fsi.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartamentoAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartamentoAllPropertiesEquals(Departamento expected, Departamento actual) {
        assertDepartamentoAutoGeneratedPropertiesEquals(expected, actual);
        assertDepartamentoAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartamentoAllUpdatablePropertiesEquals(Departamento expected, Departamento actual) {
        assertDepartamentoUpdatableFieldsEquals(expected, actual);
        assertDepartamentoUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartamentoAutoGeneratedPropertiesEquals(Departamento expected, Departamento actual) {
        assertThat(actual)
            .as("Verify Departamento auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartamentoUpdatableFieldsEquals(Departamento expected, Departamento actual) {
        assertThat(actual)
            .as("Verify Departamento relevant properties")
            .satisfies(a -> assertThat(a.getNombreDepartamento()).as("check nombreDepartamento").isEqualTo(expected.getNombreDepartamento())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartamentoUpdatableRelationshipsEquals(Departamento expected, Departamento actual) {
        assertThat(actual)
            .as("Verify Departamento relationships")
            .satisfies(a -> assertThat(a.getDireccion()).as("check direccion").isEqualTo(expected.getDireccion()));
    }
}
