package com.prueba.ppartners.repository;

import com.prueba.ppartners.model.Ticket;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findByEstatus(String estatus);

    List<Ticket> findByUsuarioId(UUID usuarioId);

    Page<Ticket> findAll(Pageable pageable);

    List<Ticket> findByEstatusAndUsuarioId(String estatus, UUID usuarioId);

}
