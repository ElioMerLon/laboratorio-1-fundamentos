import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './empleado.reducer';

export const EmpleadoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const empleadoEntity = useAppSelector(state => state.empleado.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="empleadoDetailsHeading">
          <Translate contentKey="fsijhipsterApp.empleado.detail.title">Empleado</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.id}</dd>
          <dt>
            <span id="nombres">
              <Translate contentKey="fsijhipsterApp.empleado.nombres">Nombres</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.nombres}</dd>
          <dt>
            <span id="apellidos">
              <Translate contentKey="fsijhipsterApp.empleado.apellidos">Apellidos</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.apellidos}</dd>
          <dt>
            <span id="correo">
              <Translate contentKey="fsijhipsterApp.empleado.correo">Correo</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.correo}</dd>
          <dt>
            <span id="nroCelular">
              <Translate contentKey="fsijhipsterApp.empleado.nroCelular">Nro Celular</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.nroCelular}</dd>
          <dt>
            <span id="fechacontrato">
              <Translate contentKey="fsijhipsterApp.empleado.fechacontrato">Fechacontrato</Translate>
            </span>
          </dt>
          <dd>
            {empleadoEntity.fechacontrato ? <TextFormat value={empleadoEntity.fechacontrato} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="salario">
              <Translate contentKey="fsijhipsterApp.empleado.salario">Salario</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.salario}</dd>
          <dt>
            <span id="comisionPorcentaje">
              <Translate contentKey="fsijhipsterApp.empleado.comisionPorcentaje">Comision Porcentaje</Translate>
            </span>
          </dt>
          <dd>{empleadoEntity.comisionPorcentaje}</dd>
          <dt>
            <Translate contentKey="fsijhipsterApp.empleado.inmediatosuperior">Inmediatosuperior</Translate>
          </dt>
          <dd>{empleadoEntity.inmediatosuperior ? empleadoEntity.inmediatosuperior.id : ''}</dd>
          <dt>
            <Translate contentKey="fsijhipsterApp.empleado.departamento">Departamento</Translate>
          </dt>
          <dd>{empleadoEntity.departamento ? empleadoEntity.departamento.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/empleado" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/empleado/${empleadoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default EmpleadoDetail;
