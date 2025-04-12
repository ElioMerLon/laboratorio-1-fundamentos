package com.udea.fsi.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DireccionAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDireccionAllPropertiesEquals(Direccion expected, Direccion actual) {
        assertDireccionAutoGeneratedPropertiesEquals(expected, actual);
        assertDireccionAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDireccionAllUpdatablePropertiesEquals(Direccion expected, Direccion actual) {
        assertDireccionUpdatableFieldsEquals(expected, actual);
        assertDireccionUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDireccionAutoGeneratedPropertiesEquals(Direccion expected, Direccion actual) {
        assertThat(actual)
            .as("Verify Direccion auto generated properties")
            .satisfies(a -> assertThat(a.getId()).as("check id").isEqualTo(expected.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDireccionUpdatableFieldsEquals(Direccion expected, Direccion actual) {
        assertThat(actual)
            .as("Verify Direccion relevant properties")
            .satisfies(a -> assertThat(a.getCalle()).as("check calle").isEqualTo(expected.getCalle()))
            .satisfies(a -> assertThat(a.getCodigoPostal()).as("check codigoPostal").isEqualTo(expected.getCodigoPostal()))
            .satisfies(a -> assertThat(a.getCiudad()).as("check ciudad").isEqualTo(expected.getCiudad()))
            .satisfies(a -> assertThat(a.getProvincia()).as("check provincia").isEqualTo(expected.getProvincia()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDireccionUpdatableRelationshipsEquals(Direccion expected, Direccion actual) {
        assertThat(actual)
            .as("Verify Direccion relationships")
            .satisfies(a -> assertThat(a.getPais()).as("check pais").isEqualTo(expected.getPais()));
    }
}
