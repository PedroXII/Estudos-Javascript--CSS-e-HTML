import java.util.Random;
import java.util.ArrayList;

public class Aluno extends Pessoa{
    private int matricula;
    private boolean bolsista;
    private static ArrayList<Integer> listaMatriculas = new ArrayList();
    protected static ArrayList<Aluno> listaAlunos = new ArrayList();

    public int getMatricula() {
        return matricula;
    }

    public boolean isBolsista() {
        return bolsista;
    }

    public void setBolsista(boolean bolsista) {
        this.bolsista = bolsista;
    }

    public void gerarMatricula(){
        Random aleatorio = new Random();
        int numeroMatricula;
        do {
            numeroMatricula = aleatorio.nextInt(900000) + 100000;
        }while(listaMatriculas.contains(numeroMatricula));
        listaMatriculas.add(numeroMatricula);
        this.matricula = numeroMatricula;
    }

    protected float pagarMensalidade(){
        return 500;
    }

    public boolean cadastrarAluno(long cpf, Aluno aluno){
        super.cadastrarPessoa(cpf, aluno);
        return super.cadastrar(cpf, aluno, listaAlunos);
    }

    public boolean deletarAluno(int matricula){
        int size = listaAlunos.size();
        boolean existe = false;
        for(int i = 0; i < size; i++){
            if(listaAlunos.get(i).getMatricula() == matricula){
                super.deletarPessoa(listaAlunos.get(i).getCpf());
                listaAlunos.remove(listaAlunos.get(i));
                listaMatriculas.remove(matricula);
                existe = true;
                size--;
            }
        }
        return existe;
    }

    public void ordenarAlunos(){
        boolean foiTrocado = false;
        int size = listaAlunos.size();
        int comparacao;
        do {
            foiTrocado = false;
            for (int i = 0; i < size - 1; i++) {
                comparacao = listaAlunos.get(i).getNome().compareTo(listaAlunos.get(i+1).getNome());
                if(comparacao > 0) {
                    Aluno aux = listaAlunos.get(i);
                    listaAlunos.set(i, listaAlunos.get(i+1));
                    listaAlunos.set(i+1, aux);
                    foiTrocado = true;
                }
            }
            size--;
        }while(foiTrocado);
    }

    public void listarAlunos(){
        ArrayList<String> informacoes = new ArrayList();
        for (int i = 0; i < listaAlunos.size(); i++) {
            informacoes.add("Nome: " + listaAlunos.get(i).getNome() + ", Idade: " + listaAlunos.get(i).getIdade() + ", CPF: " + listaAlunos.get(i).getCpf() + ", Ocupação: " + listaAlunos.get(i).getOcupacao() + ", Matricula: " + listaAlunos.get(i).getMatricula() + ", Bolsista: " + listaAlunos.get(i).isBolsista());
        }
        super.listar(informacoes);
    }

    public ArrayList menuDeCadastroAluno(){
        ArrayList <String> aluno = new ArrayList();
        aluno = super.menuDeCadastro("ALUNO");
        aluno.add(Boolean.toString(false));
        return aluno;
    }

    public void fazerAniversarioAluno(long cpf){
        super.fazerAniversario(cpf);
        Aluno aniversariante = new Aluno();
        for (int i = 0; i < listaAlunos.size(); i++){
            if(listaAlunos.get(i).getCpf() == cpf){
                aniversariante = listaAlunos.get(i);
                aniversariante.setIdade(aniversariante.getIdade() + 1);
                listaAlunos.set(i, aniversariante);
            }
        }
    }
    public void mensagemConfirmacaoAluno(ArrayList aluno){
        super.mensagemConfirmacao(aluno);
        System.out.println("Bolsista: " + aluno.get(4));
    }
    public Aluno obterDadosAluno(int matricula){
        Aluno aluno = new Aluno();
        for (int i = 0; i < listaAlunos.size(); i++){
            if(listaAlunos.get(i).getMatricula() == matricula){
                aluno = listaAlunos.get(i);
            }
        }
        return aluno;
    }
    public boolean existeMatricula(int matricula){
        boolean existe = false;
        for (int i = 0; i < listaAlunos.size(); i++) {
            if(listaAlunos.get(i).getMatricula() == matricula){
                existe = true;
            }
        }
        return existe;
    }
}
