package com.prueba.ppartners.controller;

import com.prueba.ppartners.model.Ticket;
import com.prueba.ppartners.service.TicketService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.crearTicket(ticket));
    }
    

    @GetMapping
    public ResponseEntity<List<Ticket>> obtenerTickets() {
        return ResponseEntity.ok(ticketService.obtenerTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerTicketPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.obtenerTicketPorId(id));
    }

    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<List<Ticket>> obtenerPorEstatus(@PathVariable String estatus) {
        return ResponseEntity.ok(ticketService.obtenerPorEstatus(estatus));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerPorUsuario(usuarioId));
    }

    @GetMapping("/tickets")
        public ResponseEntity<Page<Ticket>> obtenerTicketsPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ticketService.obtenerTicketsPaginados(page, size));
        }

    @GetMapping("/tickets/filtrar")
        public ResponseEntity<List<Ticket>> filtrarTickets(
                @RequestParam(required = false) String estatus,
                @RequestParam(required = false) UUID usuarioId) {
        return ResponseEntity.ok(ticketService.filtrarTickets(estatus, usuarioId));
    }
        

    @PutMapping("/{id}")
        public ResponseEntity<Ticket> actualizarTicket(
            @PathVariable UUID id,
            @RequestBody Ticket ticketActualizado) {
        return ResponseEntity.ok(ticketService.actualizarTicket(id, ticketActualizado));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable UUID id) {
        ticketService.eliminarTicket(id);
        return ResponseEntity.noContent().build();
    }
}
