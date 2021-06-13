package Auth;

import com.google.gson.annotations.SerializedName;

public class Authorization {
    public enum Role {
        @SerializedName("${submitter}")
        SUBMITTER {
            @Override
            public String toString() {
                return "Solicitante";
            }
        },
        @SerializedName("${developer}")
        DEVELOPER {
            @Override
            public String toString() {
                return "Desenvolvedor";
            }
        },
        @SerializedName("${projectmanager}")
        PROJECTMANAGER {
            @Override
            public String toString() {
                return "Gerente de Projeto";
            }
        },
        @SerializedName("${administrator}")
        ADMINISTRATOR {
            @Override
            public String toString() {
                return "Administrador";
            }
        };
    }
}
