package furaFila.utils;

import javax.faces.context.FacesContext;

/*
 * @Author: Wellington Gonçalves Pires
 *
 */
public class FuraFilaConstants {

    //NOME PROJETO
    public static final String TITULO_PROJETO = "FURA-FILA";

    //NOME SESSÃO
    public static final String LOGIN_SESSAO = "LOGIN";
    public static final String SERVICO_SESSAO = "SERVICO";

    //PERFIS
    public static final String SIGLA_PERFIL_1 = "A";
    public static final String SIGLA_PERFIL_2 = "L";
    public static final String SIGLA_PERFIL_3 = "C";
    public static final String SIGLA_PERFIL_4 = "E";

    public static final String SENHA_PADRAO = "123";

    public static final int CODIGO_PERFIL_1 = 1;
    public static final int CODIGO_PERFIL_2 = 2;
    public static final int CODIGO_PERFIL_3 = 3;
    public static final int CODIGO_PERFIL_4 = 4;

    // STATUS
    public static final char COD_ATIVO = '1';
    public static final char COD_INATIVO = '0';

    public static final String ATIVO = "ATIVO";
    public static final String INATIVO = "INATIVO";
    
    public static final String DISPONIVEL = "DISPONÍVEL";
    public static final String INDISPONIVEL = "INDISPONÍVEL";
    
    public static final String NAO_USADO = "NÃO USADO";
    public static final String EM_USO = "EM USO";

    public static final String ATIVAR = "Ativar";
    public static final String DESATIVAR = "Desativar";
    
    public static final int COD_LOCKER_EM_USO = 1;
    public static final int COD_LOCKER_LIVRE = 2;
    public static final int COD_EM_ANALISE = 3;
    public static final int COD_EM_SEPARACAO = 4;
    public static final int COD_ENCAMINHADO_LOCKER = 5;
    public static final int COD_APROVADO = 6;
    public static final int COD_REPROVADO = 7;
    public static final int COD_PRODUTO_ENTREGUE = 8;
    
    public static final String LOCKER_EM_USO = "LOCKER EM USO";
    public static final String LOCKER_LIVRE = "LOCKER LIVRE";
    public static final String EM_ANALISE = "EM ANALISE";
    public static final String EM_SEPARACAO = "EM SEPARAÇÃO";
    public static final String ENCAMINHADO_LOCKER = "ENCAMINHADO LOCKER";
    public static final String APROVADO = "APROVADO";
    public static final String REPROVADO = "REPROVADO";
    public static final String PRODUTO_ENTREGUE = "PRODUTO ENTREGUE";

    
    public static final int MAIOR_IDADE = 18;

    //SEXO
    public static final String MASCULINO = "MASCULINO";
    public static final String FEMININO = "FEMININO";

    public static final char COD_MASCULINO = 'M';
    public static final char COD_FEMININO = 'F';

    //SEM IMAGEM MASCULINO
    public static final String SEM_IMAGEM_MASCULINO = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "resources//semImagem_Masculino.png";

    //SEM IMAGEM FEMININO
    public static final String SEM_IMAGEM_FEMININO = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "resources//semImagem_Feminino.png";
    
    //SEM IMAGEM ESTABELECIMENTO
    public static final String SEM_IMAGEM_ESTABELECIMENTO = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "resources//semImagemEstabelecimento.png";

    //IMAGEM LOCKER
    public static final String IMAGEM_LOCKER = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "\\resources\\locker.png";
    
    //MOTIVO ENTRADA
    public static final String MOTIVO_ENTRADA_INICIAL = "ENTRADA_INICIAL";
    public static final String MOTIVO_ENTRADA_ENTRADA = "ENTRADA";

    //MOTIVO SAIDA
    public static final String MOTIVO_SAIDA_CORRECAO = "CORRECAO";
    public static final String MOTIVO_SAIDA_VENDA = "VENDA";

    //CODIGO TIPOS DIMENSAO LOCKER
    public static final int COD_TIPO_DIMENSAO_LOCKER_PEQUENO = 1;
    public static final int COD_TIPO_DIMENSAO_LOCKER_MEDIO = 2;
    public static final int COD_TIPO_DIMENSAO_LOCKER_GRANDE = 3;
    
    //==========================
    public static final String PADRAO_MONETARIO_PT_BR = "R$";
    public static final String PADRAO_MONETARIO = "0.00";

    public static final String NOME_ARQUIVO_BANCO_DADOS = "bancoDados.properties";

    public static final String PADRAO_DATA_HORA_SQL = "yyyy-MM-dd HH:mm:ss";
    public static final String PADRAO_DATA_HORA_EXIBICAO = "dd/MM/yyyy HH:mm:ss";
    public static final String PADRAO_DATA_EXIBICAO = "dd/MM/yyyy";
    public static final String PADRAO_DATA_SQL = "yyyy-MM-dd";
    public static final String PADRAO_CPF = "###.###.###-##";
    public static final String PADRAO_CNPJ = "##.###.###/####-##";
    public static final String PADRAO_CEP = "#####-###";
    public static final String PADRAO_TELEFONE = "(##)####-####";
    public static final String PADRAO_CELULAR_BRASIL = "(##)#####-####";
    public static final String PADRAO_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String BOTAO_GRAVAR = "Gravar";
    public static final String BOTAO_EDITAR = "Editar";

    public static final String UF = "uf";
    public static final String CIDADE = "cidade";
    public static final String BAIRRO = "bairro";
    public static final String TIPO_LOGRADOURO = "tipo_logradouro";
    public static final String LOGRADOURO = "logradouro";
    public static final String RESULTADO = "resultado";
    public static final String RESULTADO_TXT = "resultado_txt";

    public static final String UNIDADE = "C://";
    public static final String DIRETORIO_IMAGENS = "Fura_Fila_Imagens//";
    public static final String DIRETORIO_IMAGENS_PERFIL = "perfil//";
    public static final String DIRETORIO_IMAGENS_PRODUTOS = "produtos//";

    // -------------> GROWLS (MENSAGENS)
    // ----> TÍTULOS
    public static final String ERRO = "ERRO";
    public static final String AVISO = "AVISO";
    public static final String INFORMACAO = "SUCESSO";

    // ----> ERRO
    public static final String ERRO_GROWL_TITULO = TITULO_PROJETO + " - " + ERRO;
    public static final String ERRO_SERVIDOR_FORA = "Servidor não está respondendo";

    // ----> AVISO
    public static final String AVISO_GROWL_TITULO = TITULO_PROJETO + " - " + AVISO;
    public static final String AVISO_FALHA_LOGIN = "Usuário e/ou senhas inválidos";
    public static final String AVISO_CEP_VAZIO = "Campo CEP vazio";
    public static final String AVISO_CAMPOS_VAZIOS = "Os campos estão vazios";
    public static final String AVISO_CPF_INVALIDO = "CPF inválido!";
    public static final String AVISO_EMAIL_INVALIDO = "Email inválido!";
    public static final String AVISO_CADASTRO_NAO_ATIVO = "O seu cadastro não está ativo!";
    public static final String AVISO_TIPO_PRODUTO_EXISTE = "Tipo de produto fornecido já existe";
    public static final String AVISO_ENTREGADOR_EXISTE = "Entregador fornecido já existe";
    
    public static final String CAMPOS_VAZIOS(String campo){
        return "Campo " + campo + " vazio!";
    }
    
    public static final String DUPLICIDADE_CAMPOS(String campo){
        return "O valor " + campo + " já existe!";
    }

    // ----> INFORMAÇÃO
    public static final String INFO_GROWL_TITULO = TITULO_PROJETO + " - " + INFORMACAO;
    public static final String INFO_CLIENTE_CADASTRADO = "Cliente cadastrado com sucesso!";
    public static final String INFO_CPF_VALIDO = "CPF válido!";
    public static final String INFO_EMAIL_VALIDO = "Email válido!";
    public static final String INFO_PRODUTO_AD_CARRINHO = "Produto adicionado ao carrinho!";
    public static final String INFO_PRODUTO_DEL_CARRINHO = "Produto removido do carrinho!";
    public static final String INFO_ENTREGADOR_CADASTRADO = "Entregador cadastrado com sucesso!";
    public static final String INFO_ENTREGADOR_ALTERADO = "Entregador alterado com sucesso!";
    public static final String INFO_TIPO_PRODUTO_CADASTRADO = "Tipo Produto cadastrado com sucesso!";

    // -------------> SESSÕES
    public static final String SESSAO_LOGIN = "login";
    public static final String SESSAO_CLIENTE = "cliente";
    public static final String SESSAO_ESTABELECIMENTO = "estabelecimento";
    public static final String SESSAO_ESTABELECIMENTO_LOGIN = "estabelecimentoLogin";

    //------------------------> EMAILS
    public static final String EMAIL_OFICIAL = "sistema.furafila@gmail.com";
    public static final String ASSUNTO_BOAS_VINDAS = "Bem vindo ao Fura Fila, ";
    public static final String ASSUNTO_STATUS = "Seu pedido mudou de status!";
    
    public static final String CAMINHO_EMAIL_EM_ANALISE = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "\\emails\\TemplateEmAnalise.html";
    public static final String CAMINHO_EMAIL_EM_SEPARACAO = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "\\emails\\TemplateSeparacao.html";
    public static final String CAMINHO_EMAIL_ENCAMINHADO_LOCKER = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "\\emails\\TemplateEncaminhadoLocker.html";

}
