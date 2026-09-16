package com.uninter.raiazesdonordeste.pedido.domain.model;

public enum StatusPedido {
    CRIADO(1),
    AGUARDANDO_PAGAMENTO(2),
    CONFIRMADO(3),
    EM_PREPARO(4),
    PRONTO(5),
    ENTREGUE(6),
    CANCELADO(7);

    private final int indice;

    StatusPedido(int indice) {
        this.indice = indice;
    }

    public static boolean Isnext(StatusPedido statusAtual, StatusPedido statusFuturo) {
        return statusAtual.getIndice() < statusFuturo.getIndice();
    }

    private int getIndice() {
        return this.indice;
    }
}
