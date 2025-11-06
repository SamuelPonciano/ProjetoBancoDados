package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venda {
    private int id;
    private int vendedorId;
    private int transportadoraId;
    private String enderecoDestino;
    private int clienteId;
    private BigDecimal valor;
    private LocalDateTime feitaEm;
    private List<Produto> produtos;
}