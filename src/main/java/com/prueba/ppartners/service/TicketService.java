package com.prueba.ppartners.service;

import com.prueba.ppartners.model.Ticket;
import com.prueba.ppartners.model.Usuario;
import com.prueba.ppartners.repository.TicketRepository;
import com.prueba.ppartners.repository.UsuarioRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;        
    }

    public Ticket crearTicket(Ticket ticket) {
        if (ticket.getUsuario() == null || ticket.getUsuario().getId() == null) {
            throw new IllegalArgumentException("El ticket debe estar asociado a un usuario válido.");
        }
        return ticketRepository.save(ticket);
    }
    

    public List<Ticket> obtenerTickets() {
        return ticketRepository.findAll();
    }

    public Ticket obtenerTicketPorId(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
    }

    public List<Ticket> obtenerPorEstatus(String estatus) {
        return ticketRepository.findByEstatus(estatus);
    }    

    public List<Ticket> obtenerPorUsuario(UUID usuarioId) {
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    public Ticket actualizarTicket(UUID id, Ticket ticketActualizado) {
        return ticketRepository.findById(id).map(ticket -> {
            if (ticketActualizado.getEstatus() != null) {
                ticket.setEstatus(ticketActualizado.getEstatus());
            }

            if (ticketActualizado.getUsuario() != null && ticketActualizado.getUsuario().getId() != null) {
                Usuario usuario = usuarioRepository.findById(ticketActualizado.getUsuario().getId())
                        .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + ticketActualizado.getUsuario().getId() + " no existe."));
                ticket.setUsuario(usuario);
            }

            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new IllegalArgumentException("El ticket con ID " + id + " no existe."));
    }

    public List<Ticket> filtrarTickets(String estatus, UUID usuarioId) {
        if (estatus != null && usuarioId != null) {
            return ticketRepository.findByEstatusAndUsuarioId(estatus, usuarioId);
        } else if (estatus != null) {
            return ticketRepository.findByEstatus(estatus);
        } else if (usuarioId != null) {
            return ticketRepository.findByUsuarioId(usuarioId);
        }
        return ticketRepository.findAll();
    }     
    
    public void eliminarTicket(UUID id) {
        if (!ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("El ticket con ID " + id + " no existe.");
        }
        ticketRepository.deleteById(id);
    }

    public Page<Ticket> obtenerTicketsPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ticketRepository.findAll(pageable);
    }

    
}
