import java.io.Serializable;

public class LigaSportowa implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nameDyscypline;
    private int numberClassDivisions;
    private int numberTeam;
    private String nameLeadingLeadBody;

    public LigaSportowa(){}

    public LigaSportowa(String nameDyscypline, int numberClassDivisions, int numberTeam, String nameLeadingLeadBody) {
        this.nameDyscypline = nameDyscypline;
        this.numberClassDivisions = numberClassDivisions;
        this.numberTeam = numberTeam;
        this.nameLeadingLeadBody = nameLeadingLeadBody;
    }

    public String getNameDyscypline() {
        return nameDyscypline;
    }

    public void setNameDyscypline(String nameDyscypline) {
        this.nameDyscypline = nameDyscypline;
    }

    public int getNumberClassDivisions() {
        return numberClassDivisions;
    }

    public void setNumberClassDivisions(int numberClassDivisions) {
        this.numberClassDivisions = numberClassDivisions;
    }

    public int getNumberTema() {
        return numberTeam;
    }

    public void setNumberTema(int numberTema) {
        this.numberTeam = numberTema;
    }

    public String getNameLeadingLeadBody() {
        return nameLeadingLeadBody;
    }

    public void setNameLeadingLeadBody(String nameLeadingLeadBody) {
        this.nameLeadingLeadBody = nameLeadingLeadBody;
    }

    @Override
    public String toString() {
        return "LigaSportowa{" +
                "nameDyscypline='" + nameDyscypline + '\'' +
                ", numberClassDivisions=" + numberClassDivisions +
                ", numberTema=" + numberTeam +
                ", nameLeadingLeadBody='" + nameLeadingLeadBody + '\'' +
                '}';
    }
}
