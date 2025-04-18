package com.udea.fsi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Trabajo.
 */
@Entity
@Table(name = "trabajo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo_trabajo")
    private String tituloTrabajo;

    @Column(name = "salario_min")
    private Long salarioMin;

    @Column(name = "salario_max")
    private Long salarioMax;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_trabajo__tarea",
        joinColumns = @JoinColumn(name = "trabajo_id"),
        inverseJoinColumns = @JoinColumn(name = "tarea_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "trabajos" }, allowSetters = true)
    private Set<Tarea> tareas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "trabajos", "inmediatosuperior", "departamento", "contrato" }, allowSetters = true)
    private Empleado empleado;

    @JsonIgnoreProperties(value = { "trabajo", "departamento", "empleado" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "trabajo")
    private Contrato contrato;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Trabajo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloTrabajo() {
        return this.tituloTrabajo;
    }

    public Trabajo tituloTrabajo(String tituloTrabajo) {
        this.setTituloTrabajo(tituloTrabajo);
        return this;
    }

    public void setTituloTrabajo(String tituloTrabajo) {
        this.tituloTrabajo = tituloTrabajo;
    }

    public Long getSalarioMin() {
        return this.salarioMin;
    }

    public Trabajo salarioMin(Long salarioMin) {
        this.setSalarioMin(salarioMin);
        return this;
    }

    public void setSalarioMin(Long salarioMin) {
        this.salarioMin = salarioMin;
    }

    public Long getSalarioMax() {
        return this.salarioMax;
    }

    public Trabajo salarioMax(Long salarioMax) {
        this.setSalarioMax(salarioMax);
        return this;
    }

    public void setSalarioMax(Long salarioMax) {
        this.salarioMax = salarioMax;
    }

    public Set<Tarea> getTareas() {
        return this.tareas;
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Trabajo tareas(Set<Tarea> tareas) {
        this.setTareas(tareas);
        return this;
    }

    public Trabajo addTarea(Tarea tarea) {
        this.tareas.add(tarea);
        return this;
    }

    public Trabajo removeTarea(Tarea tarea) {
        this.tareas.remove(tarea);
        return this;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Trabajo empleado(Empleado empleado) {
        this.setEmpleado(empleado);
        return this;
    }

    public Contrato getContrato() {
        return this.contrato;
    }

    public void setContrato(Contrato contrato) {
        if (this.contrato != null) {
            this.contrato.setTrabajo(null);
        }
        if (contrato != null) {
            contrato.setTrabajo(this);
        }
        this.contrato = contrato;
    }

    public Trabajo contrato(Contrato contrato) {
        this.setContrato(contrato);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trabajo)) {
            return false;
        }
        return getId() != null && getId().equals(((Trabajo) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Trabajo{" +
            "id=" + getId() +
            ", tituloTrabajo='" + getTituloTrabajo() + "'" +
            ", salarioMin=" + getSalarioMin() +
            ", salarioMax=" + getSalarioMax() +
            "}";
    }
}
