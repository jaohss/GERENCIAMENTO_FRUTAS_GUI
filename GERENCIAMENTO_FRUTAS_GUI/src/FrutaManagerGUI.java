import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrutaManagerGUI{
    //Definição dos atributos de ArrayList
    private ArrayList<String> frutas;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public FrutaManagerGUI(){
        //Instanciando objetos Jlist
        frutas = new ArrayList<>(); //Instanciando objeto para uma ArrayList
        listModel = new DefaultListModel<>();//Instanciando obejto para uma DefaultListModel, usado na interface gráfica

        //Definição das configurações do frame
        JFrame frame = new JFrame("Gerenciador de Frutas");//Instanciando objeto de JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Define o comportamento da janela, quando o usuário tentar fecha-la
        frame.setSize(600,300);//Definindo tamanho da janela
        frame.setLayout(new BorderLayout());

        //Instanciando obejto e definição da configuração do Panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //Definição das configurações da Label
        JLabel label1 = new JLabel("Digite a fruta desejada");
        panel.add(label1);//Adiciona a label configurada no panel selecionado

        //Definição das configurações d o TextField
        JTextField frutaField = new JTextField(15);
        panel.add(frutaField);//Adiciona o TextField no panel selecionado

        //Instanciando o botão adicionar e seu conteúdo interno
        JButton botaoAdicionar = new JButton("Adicionar");
        panel.add(botaoAdicionar);//Adiciona o botão adicionar no panel selecionado

        //Instanciando o botão modificar e seu conteúdo interno
        JButton botaoModificar = new JButton("Modificar");
        botaoModificar.setEnabled(false);//Define o status de atividade do botão
        panel.add(botaoModificar);//Adiciona o botão modificar no panel selecionado

        //Instanciando o botão modificar e seu conteúdo interno
        JButton botaoRemover = new JButton("Remover");
        botaoRemover.setEnabled(false);//Define o status de atividade do botão
        panel.add(botaoRemover);//Adiciona o botão modificar no panel selecionado

        //Adicionando o panel dentro do frame na posição norte, ou seja, todas as configurações dentro da "panel" serão inseridas ao norte do frame
        frame.add(panel, BorderLayout.NORTH);

        //Instância do objeto list da JList e adiciona uma barra de rolagem, para que o usuário role a lista caso haja mais itens a serem exibidos
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);

        //Instanciando o botão listar e seu conteúdo interno
        JButton botaoListar = new JButton("Listar Frutas");
        frame.add(botaoListar, BorderLayout.SOUTH);//Definição da posição do botão listar

        //Método para definir oque deve acontecer quando o botão adicionar for clicado
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //Obtem o texto digitado dentro da frutaField e o armazena na variável novaFruta
                String novaFruta = frutaField.getText();
                //Caso o campo novaFruta esteja vazio não vai acontecer nada, caso o contrário, será executado o bloco de código abaixo definido
                if(!novaFruta.isEmpty()){
                    frutas.add(novaFruta);//Adiciona a fruta digitada a lista frutas
                    listModel.addElement(novaFruta);//Adiciona a fruta digitada na list exibida na interface gráfica
                    frutaField.setText("");//Limpa o campo de texto, para que outra fruta possa ser digitada
                    JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada.");//Abre uma tela com mensagem de diálogo com a mensagem que a fruta digitada foi adicionada
                }
            }
        });

        //Método para definir oque deve acontecer quando o botão Modificar for clicado
        botaoModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedIndex = list.getSelectedIndex();//Pega o índice do item selecionado, se não estiver selecionado, selectedIndex vai ter o valor -1
                if(selectedIndex != -1){//Verifica se um item está selecionado na lista
                    String frutaSelecionada = listModel.getElementAt(selectedIndex);//obtem o item selecinado de listModel(JList) de acordo com o índice selecionado
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada);//Exibe uma caixa de diálogo de entrada para o usuário definir a nova fruta ser modificada
                    if(novaFruta != null && !novaFruta.isEmpty()){//verifica se o campo novaFruta está vazio e nulo
                        frutas.set(selectedIndex, novaFruta);//atualiza na lista frutas com novo valor no índice selecionado
                        listModel.set(selectedIndex, novaFruta);
                        JOptionPane.showMessageDialog(frame, "Fruta "+frutaSelecionada + " foi modificado");//abre uma caixa de diálogo de mensagem e exibe que a fruta selecionada foi modificado
                    }
                }   else{
                        JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar. ");
                    }
            }
        });

        //Método para definir oque deve acontecer quando o botão remover for clicado
        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int selectedIndex = list.getSelectedIndex();//Pega o índice do item selecionado, se não estiver selecionado, selectedIndex vai ter o valor -1
                if(selectedIndex != -1){//Verifica se um item está selecionado na lista
                    String frutaRemovida = frutas.remove(selectedIndex);//faz a remoção da fruta selecionada com base do indice na list frutas
                    listModel.remove(selectedIndex); //faz a remoção da fruta selecionada com base no índice na listModel
                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");//Abre a caixa de diálogo de mensagem de confirmação que a fruta selecionada foi removida
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover. ");
                }
            }
        });
        //Método para definir oque deve acontecer quando o botão remover for clicado
        botaoListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(frutas.isEmpty()){//verifica se a lista está vazia
                    //caso a lsita esteja vazia, será exibida uma caixa de diálogo com a mensagem que nenhuma fruta na lista
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista");
                }
                else{
                    //caso a lista possua itens, será exibida a caixa de diálogo com a mensagem da frutas que estão dentro da lista
                    JOptionPane.showMessageDialog(frame, "Frutas: "+frutas);
                }
            }
        });

        list.addListSelectionListener(e ->{
            boolean selectionExists = !list.isSelectionEmpty();//variável que verifica se há algum item selecionado pelo list.isSelectionEmpty
            botaoRemover.setEnabled(selectionExists);//Definição do status de atividade do botão remover
            botaoModificar.setEnabled(selectionExists);//definição do status de atividade do botão modificar
        });

        // define a visibilidade do frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Método que garante a inicialização correta de interface gráfica
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new FrutaManagerGUI();
            }
        });
    }
}