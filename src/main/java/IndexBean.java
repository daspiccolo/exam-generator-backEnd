import br.com.dasp.examgeneratorfront.persistence.dao.LoginDAO;
import br.com.dasp.examgeneratorfront.persistence.dao.ProfessorDAO;
import br.com.dasp.examgeneratorfront.persistence.model.Token;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexBean implements Serializable {
    private String message = "WOOOOOOOOOOOOOOOOORKING!";
    private final LoginDAO loginDAO;
    private final ProfessorDAO professorDAO;

    @Inject
    public IndexBean(LoginDAO loginDAO, ProfessorDAO professorDAO) {
        this.loginDAO = loginDAO;
        this.professorDAO = professorDAO;
    }

    public void login() throws IOException {
        Token token = loginDAO.loginReturnToken("debora", "debora");
        System.out.println(token);
    }
    public void checkProfessor(){
        professorDAO.getProfessorById(1L);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

