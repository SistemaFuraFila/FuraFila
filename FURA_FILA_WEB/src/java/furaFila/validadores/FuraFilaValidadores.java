package furaFila.validadores;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estabelecimento.service.EstabelecimentoService;
import furaFila.mvc.login.model.Login;
import furaFila.mvc.login.service.LoginService;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class FuraFilaValidadores {

    private final static EstabelecimentoService estabelecimentoService = new EstabelecimentoService();
    private final static LoginService loginService = new LoginService();
    
    public static boolean validarEstabelecimento(Estabelecimento estabelecimento) throws Exception{
        
        if(razaoSocialVazio(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.CAMPOS_VAZIOS("Razão Social"));
            return false;
        }
        if(emailVazio(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.CAMPOS_VAZIOS("Email"));
            return false;
        }
        if(cnpjVazio(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.CAMPOS_VAZIOS("CNPJ"));
            return false;
        }
        if(inscricaoEstadualVazio(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.CAMPOS_VAZIOS("Inscrição Estadual"));
            return false;
        }
        if(razaoSocialExiste(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.DUPLICIDADE_CAMPOS("Razão Social"));
            return false;
        }
        if(emailExiste(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.DUPLICIDADE_CAMPOS("Email"));
            return false;
        }
        if(cnpjExiste(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.DUPLICIDADE_CAMPOS("CNPJ"));
            return false;
        }
        if(inscricaoSocialExiste(estabelecimento)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.DUPLICIDADE_CAMPOS("Inscrição Estadual"));
            return false;
        }
        
        return true;
        
    }
    
    public static boolean validarLogin(Login login) throws Exception{
        
        if(usuarioVazio(login)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.CAMPOS_VAZIOS("Usuário"));
            return false;
        }
        if(senhaVazia(login)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.CAMPOS_VAZIOS("Senha"));
            return false;
        }
        if(usuarioExiste(login)){
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.DUPLICIDADE_CAMPOS("Usuário"));
            return false;
        }
        
        return true;
        
    }
        
    
    private static boolean razaoSocialVazio(Estabelecimento estabelecimento) {
        return FuraFilaUtils.valorVazioNulo(estabelecimento.getRazao_social());
    }

    private static boolean emailVazio(Estabelecimento estabelecimento) {
        return FuraFilaUtils.valorVazioNulo(estabelecimento.getEmail());
    }

    private static boolean cnpjVazio(Estabelecimento estabelecimento) {
        return FuraFilaUtils.valorZerado(estabelecimento.getCnpj());
    }

    private static boolean inscricaoEstadualVazio(Estabelecimento estabelecimento) {
        return FuraFilaUtils.valorZerado(estabelecimento.getInscricao_estadual());
    }

    private static boolean razaoSocialExiste(Estabelecimento estabelecimento) throws Exception {
        return getEstabelecimentoService().verificarDuplicidadeRazaoSocial(estabelecimento) > 0;
    }

    private static boolean emailExiste(Estabelecimento estabelecimento) throws Exception {
        return getEstabelecimentoService().verificarDuplicidadeEmail(estabelecimento) > 0;
    }

    private static boolean cnpjExiste(Estabelecimento estabelecimento) throws Exception {
        return getEstabelecimentoService().verificarDuplicidadeCnpj(estabelecimento) > 0;
    }

    private static boolean inscricaoSocialExiste(Estabelecimento estabelecimento) throws Exception {
        return getEstabelecimentoService().verificarDuplicidadeInscricaoEstadual(estabelecimento) > 0;
    }

    private static boolean usuarioVazio(Login login) {
        return FuraFilaUtils.valorVazioNulo(login.getUsuario());
    }

    private static boolean senhaVazia(Login login) {
        return FuraFilaUtils.valorVazioNulo(login.getSenha());
    }

    private static boolean usuarioExiste(Login login) throws Exception{
        return getLoginService().verificarDuplicidade(login) > 0;
    }

    public static EstabelecimentoService getEstabelecimentoService() {
        return estabelecimentoService;
    }

    public static LoginService getLoginService() {
        return loginService;
    }



}
