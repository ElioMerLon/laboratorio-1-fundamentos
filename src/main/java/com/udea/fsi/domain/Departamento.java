package com.udea.fsi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Departamento.
 */
@Entity
@Table(name = "departamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nombre_departamento", nullable = false)
    private String nombreDepartamento;

    @JsonIgnoreProperties(value = { "pais", "departamento" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Direccion direccion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departamento")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "trabajos", "inmediatosuperior", "departamento", "contrato" }, allowSetters = true)
    private Set<Empleado> empleados = new HashSet<>();

    @JsonIgnoreProperties(value = { "trabajo", "departamento", "empleado" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "departamento")
    private Contrato contrato;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Departamento id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return this.nombreDepartamento;
    }

    public Departamento nombreDepartamento(String nombreDepartamento) {
        this.setNombreDepartamento(nombreDepartamento);
        return this;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Departamento direccion(Direccion direccion) {
        this.setDireccion(direccion);
        return this;
    }

    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        if (this.empleados != null) {
            this.empleados.forEach(i -> i.setDepartamento(null));
        }
        if (empleados != null) {
            empleados.forEach(i -> i.setDepartamento(this));
        }
        this.empleados = empleados;
    }

    public Departamento empleados(Set<Empleado> empleados) {
        this.setEmpleados(empleados);
        return this;
    }

    public Departamento addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
        empleado.setDepartamento(this);
        return this;
    }

    public Departamento removeEmpleado(Empleado empleado) {
        this.empleados.remove(empleado);
        empleado.setDepartamento(null);
        return this;
    }

    public Contrato getContrato() {
        return this.contrato;
    }

    public void setContrato(Contrato contrato) {
        if (this.contrato != null) {
            this.contrato.setDepartamento(null);
        }
        if (contrato != null) {
            contrato.setDepartamento(this);
        }
        this.contrato = contrato;
    }

    public Departamento contrato(Contrato contrato) {
        this.setContrato(contrato);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Departamento)) {
            return false;
        }
        return getId() != null && getId().equals(((Departamento) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Departamento{" +
            "id=" + getId() +
            ", nombreDepartamento='" + getNombreDepartamento() + "'" +
            "}";
    }
}
