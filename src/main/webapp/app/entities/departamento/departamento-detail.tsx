import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './departamento.reducer';

export const DepartamentoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const departamentoEntity = useAppSelector(state => state.departamento.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="departamentoDetailsHeading">
          <Translate contentKey="fsijhipsterApp.departamento.detail.title">Departamento</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{departamentoEntity.id}</dd>
          <dt>
            <span id="nombreDepartamento">
              <Translate contentKey="fsijhipsterApp.departamento.nombreDepartamento">Nombre Departamento</Translate>
            </span>
          </dt>
          <dd>{departamentoEntity.nombreDepartamento}</dd>
          <dt>
            <Translate contentKey="fsijhipsterApp.departamento.direccion">Direccion</Translate>
          </dt>
          <dd>{departamentoEntity.direccion ? departamentoEntity.direccion.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/departamento" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/departamento/${departamentoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DepartamentoDetail;
