package furaFila.utils;

/**
 *
 * @author Gabriel Sanches
 */
public interface BancoDadosConstants {

    //String NOME_DA_TABELA = "NOME_DA_TABELA";
    //String NOME_DA_TABELA_NOME_DO_CAMPO = "nomeDoCampo";

    // Permissao
    String _PEMISSAO = "PERMISSAO";
    String _PERMISSAO_ID_PERMISSAO = "id_permissao";
    String _PERMISSAO_PERMISSAO = "permissao";
    String _PERMISSAO_SIGLA_PERMISSAO = "sigla_permissao";

    //Login
    String _LOGIN = "LOGIN";
    String _LOGIN__ID_LOGIN = "id_login";
    String _LOGIN_USUARIO = "usuario";
    String _LOGIN_SENHA = "senha";
    String _LOGIN_ID_PERMISSAO_FK = "id_permissao";

    //Tipo_Servico
    String _TIPO_SERVICO = "TIPO_SERVICO";
    String _TIPO_SERVICO_ID_TIPO_SERV = "id_tipo_serv";
    String _TIPO_SERVICO_TIPO_SERV_DESC = "tipo_serv_desc";
    String _TIPO_SERVICO_STATUS = "status";

    // Servico
    String _SERVICO = "SERVICO";
    String _SERVICO_ID_SERV = "id_serv";
    String _SERVICO_SERV_DESC = "serv_desc";
    String _SERVICO_STATUS = "status";
    String _SERVICO_ID_TIPO_SERV_FK = "id_tipo_serv_Fk";
    String _SERVICO_ID_LOGIN_FK = "id_login_FK";

    //Tipo_Produto
    String _TIPO_PRODUTO = "TIPO_PRODUTO";
    String _TIPO_PRODUTO_ID_TIPO_PRODUTO = "id_tipo_produto";
    String _TIPO_PRODUTO_TIPO_PRODUTO_DESC = "tipo_produt_desc";

    //Produto
    String _PRODUTO = "PRODUTO";
    String _PRODUTO_ID_PRODUTO = "id_produto";
    String _PRODUTO_PRODUTO_DESC = "produto_desc";
    String _PRODUTO_VALOR_UNITARIO = "valor_unitario";
    String _PRODUTO_QTD_MINIMA = "qtd_minima";
    String _PRODUTO_QTD_MAXIMA = "qtd_maxima";
    String _PRODUTO_ID_TIPO_PRODUTO_FK = "id_tipo_produto_FK";

    //ESTOQUE
    String _ESTOQUE = "ESTOQUE";
    String _ESTOQUE_ID_ESTOQUE = "id_estoque";
    String _ESTOQUE_ID_SERV_FK = "id_serv_FK";

    //ESTOQUE_PRODUTOS
    String _ESTOQUE_PRODUTOS = "ESTOQUE_PRODUTOS";
    String _ESTOQUE_PRODUTOS_ID_ESTOQUE_PRODUTO = "id_estoque_produto";
    String _ESTOQUE_PRODUTOS_QTD_ESTOQUE = "qtdEstoque";
    String _ESTOQUE_PRODUTOS_ID_PRODUTO_FK = "id_produto_FK";
    String _ESTOQUE_PRODUTOS_ID_ESTOQUE_FK = "id_estoque_FK";

    //FORMA_PAGAMENTO
    String _FORMA_PAGAMENTO = "FORMA_PAGAMENTO";
    String _FORMA_PAGAMENTO_ID_FORMA_PAGAMENTO = "id_forma_pagamento";
    String _FORMA_PAGAMENTO_FORMA_PAGAMENTO = "forma_pagamento";

    //Status
    String _STATUS = "STATUS";
    String _STATUS_ID_STATUS = "id_status";
    String _STATUS_STATUS = "status";

    //Comanda
    String _COMANDA = "COMANDA";
    String _COMANDA_ID_COMANDA = "id_comanda";
    String _COMANDA_TOTALCOMPRA = "totalCompra";
    String _COMANDA_QTDTOTAL = "qtdTotal";
    String _COMANDA_DATAVENDA = "dataVenda";
    String _COMANDA_DATACONFIRMACAO = "dataConfirmacao";
    String _COMANDA_ID_FORMA_PAGAMENTO_FK = "forma_pagamento_FK";
    String _COMANDA_ID_SERV_FK = "id_serv_FK";
    String _COMANDA_ID_LOGIN_FK = "id_login_FK";
    String _COMANDA_STATUS_FK = "status_FK";

    //Pedidos
    String _PEDIDOS = "PEDIDOS";
    String _PEDIDOS_ID_PEDIDOS = "id_pedidos";
    String _PEDIDOS_QTD = "qtd";
    String _PEDIDOS_SUBTOTAL = "subTotal";
    String _PEDIDOS_ID_PRODUTO_FK = "id_produto_FK";
    String _PEDIDOS_ID_COMANDA_FK = "id_comanda_FK";

    //Motivo_Entrada
    String _MOTIVO_ENTRADA = "MOTIVO_ENTRADA";
    String _MOTIVO_ENTRADA_ID_MOTIVO_ENTRADA = "id_motivo_entrada";
    String _MOTIVO_ENTRADA_QTDENTRADA = "qtdEntrada";
    String _MOTIVO_ENTRADA_DATA_ENTRADA = "dataEntrada";
    String _MOTIVO_ENTRADA_ID_PRODUTO_FK = "id_produto_FK";

    //Motivo_Saida
    String _MOTIVO_SAIDA = "MOTIVO_SAIDA";
    String _MOTIVO_SAIDA_ID_MOTIVO = "id_motivo";
    String _MOTIVO_SAIDA_MOTIVOSAIDA = "motivoSaida";

    //Estoque_Saida
    String _ESTOQUE_SAIDA = "ESTOQUE_SAIDA";
    String _ESTOQUE_SAIDA_ID_ESTOQUE_SAIDA = "id_estoque_saida";
    String _ESTOQUE_SAIDA_QTDSAIDA = "qtdSaida";
    String _ESTOQUE_SAIDA_DATASAIDA = "dataSaida";
    String _ESTOQUE_SAIDA_ID_MOTIVO_FK = "id_motivo_FK";
    String _ESTOQUE_SAIDA_ID_PRODUTO_FK = "id_produto_FK";
    
    
}
