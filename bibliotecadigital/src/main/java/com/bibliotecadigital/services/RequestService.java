package com.bibliotecadigital.services;

import java.util.List;

import com.bibliotecadigital.dao.RequestDao;
import com.bibliotecadigital.entities.Loan;
import com.bibliotecadigital.services.dtos.LoanDto;

/**
 * Clase servicio encargada de gestionar la lógica de
 * negocio relacionada con la entidad Loan.
 * 
 * Actúa como intermediario entre el controlador (Servlet)
 * y la capa de acceso a datos (DAO).
 * 
 * Utiliza el DAO para realizar operaciones sobre los préstamos.
 * 
 * @author Valeria Oliva Valdes
 * @version 1.0
 */
public class RequestService {
   /**
     * Clase DAO utilizada para acceder y gestionar
     * los datos de los préstamos.
     */
    private RequestDao dao = new RequestDao();

    /**
     * Registra la solicitud de préstamo de un libro por parte
     * de un usuario.
     * 
     * Crea una instancia de Loan con la información del usuario
     * y del libro. Establece el estado inicial del préstamo y delega
     * la operación al DAO correspondiente (RequestDao)
     * 
     * @param userId identificador único del usuario
     * @param bookId identificador único del libro
     * @return      boolean proveniente del resultado del método DAO
     */
    public boolean requestBook(int userId, int bookId) {
        Loan loan = new Loan();

        loan.setBookId(bookId);
        loan.setUserId(userId);
        loan.setStatus("Prestado");

        return dao.addRequest(loan);
    }

    /**
     * Obtiene el listado de préstamos asociados a un
     * usuario específico.
     * 
     * @param userId identificador único del usuario
     * @return listado de préstamos utilizando el identificador del
     *         usuario
     */
    public List<LoanDto> getLoansByUser(int userId) {
      return dao.loanList(userId);
    }

    /**
     * Gestiona la devolución de un libro prestado.
     * 
     * Delega la operación al DAO correspondiente (RequestDao)
     * para actualizar el estado del préstamo y la disponibilidad
     * del libro.
     * 
     * @param loanId identificador único del préstamo
     * @param bookId identificador único del libro
     */
    public void returnLoan(int loanId, int bookId){
        dao.returnBook(loanId, bookId);
    }
}
