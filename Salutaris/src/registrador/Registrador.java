package registrador;

import servidor.Comando;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Registrador
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        ColecaoInstituicoes colinst;
        colinst = new ColecaoInstituicoes();
        try
        {
            colinst.recuperArquivo();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        while(menu(input,colinst));
    }

    private static boolean menu(Scanner input, ColecaoInstituicoes colinst)
    {
        System.out.println("Escolha uma das opções abaixo:\n" +
                           "1 - Menu Instituições\n" +
                           "2 - Menu Blocos\n" +
                           "3 - Menu Salas\n" +
                           "4 - Menu Dispositivos\n" +
                           "5 - Encerrar Aplicação");
        try
        {
            switch(lerOpcao(input,1,5))
            {
                case 1:
                    while(menuInstituicoes(input,colinst));
                    colinst.gravaArquivo();
                    atualizaServidor(colinst);
                    return true;
                case 2:
                    while(menuBlocos(input,colinst));
                    colinst.gravaArquivo();
                    atualizaServidor(colinst);
                    return true;
                case 3:
                    while(menuSalas(input,colinst));
                    colinst.gravaArquivo();
                    atualizaServidor(colinst);
                    return true;
                case 4:
                    while(menuDispositivos(input,colinst));
                    colinst.gravaArquivo();
                    atualizaServidor(colinst);
                    return true;
                default:
                    return false;
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        return true;
    }

    private static boolean menuInstituicoes(Scanner input, ColecaoInstituicoes colinst) throws Exception
    {
        Instituicao inst = null;
        String nome;
        String cidade;
        System.out.println("Escolha uma das opções abaixo:\n" +
                           "1 - Cadastrar Instituicao de Ensino\n" +
                           "2 - Cadastrar Empresa\n" +
                           "3 - Listar Instituições\n" +
                           "4 - Listar Instituições de Ensino\n" +
                           "5 - Listar Empresas\n" +
                           "6 - Alterar Nome da Instituição\n" +
                           "7 - Desvincular Instituicao\n" +
                           "8 - Retornar ao Menu Anterior");
        switch(lerOpcao(input,1,8))
        {
            case 1:
                inst = null;
                InstituicaoEnsino instEns = null;
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição de ensino: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                System.out.print("Campus: ");
                String campus = input.nextLine();
                instEns = new InstituicaoEnsino(nome, cidade, campus);
                if(!colinst.adicionaInstituicaoEnsino(instEns))
                {
                    throw new Exception("A instituição já havia sido cadastrada!");
                }
                System.out.println("Instituição de ensino cadastrada com sucesso!");
                return true;
            case 2:
                inst = null;
                Empresa empresa = null;
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Empresa: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                System.out.print("Unidade: ");
                String unidade = input.nextLine();
                empresa = new Empresa(nome, cidade, unidade);
                if(!colinst.adicionaEmpresa(empresa))
                {
                    throw new Exception("A empresa já havia sido cadastrada!");
                }
                System.out.println("Empresa cadastrada com sucesso!");
                return true;
            case 3:
                if(colinst.listagemInstituicoes() == 0)
                {
                    throw new Exception("Ainda não há instituições cadastradas!");
                }
                return true;
            case 4:
                if(colinst.listagemInstEns() == 0)
                {
                    throw new Exception("Ainda não há instituições de ensino cadastradas!");
                }
                return true;
            case 5:
                if(colinst.listagemEmpresas() == 0)
                {
                    throw new Exception("Ainda não há empresas cadastradas!");
                }
                return true;
            case 6:
                inst = null;
                if(colinst.qtdIns() == 0)
                {
                    throw new Exception("Ainda não há instituições cadastradas!");
                }
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst instanceof Empresa)
                {
                    System.out.print("Unidade: ");
                    unidade = input.nextLine();
                    inst = colinst.procuraEmpresa(new Empresa(nome, cidade, unidade));
                    System.out.print("Novo nome da empresa:");
                }
                else
                {
                    System.out.print("Campus: ");
                    campus = input.nextLine();
                    inst = colinst.procuraInstEns(new InstituicaoEnsino(nome, cidade, campus));
                    System.out.print("Novo nome da instituição de ensino:");
                }
                nome = input.nextLine();
                inst.setNome(nome);
                System.out.println("Nome alterado com sucesso!");
                return true;
            case 7:
                inst = null;
                if(colinst.qtdIns() == 0)
                {
                    throw new Exception("Ainda não há instituições cadastradas!");
                }
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst instanceof Empresa)
                {
                    System.out.print("Unidade: ");
                    unidade = input.nextLine();
                    inst = colinst.procuraEmpresa(new Empresa(nome, cidade, unidade));
                    System.out.print("Tem certeza que deseja desvincular a unidade " + ((Empresa)inst).getUnidade() + ", da cidade " + inst.getCidade() + " da empresa " + inst.getNome() + "? (S/N):");
                    String opcaoSN = input.nextLine();
                    if(opcaoSN.toLowerCase().equals("s"))
                    {
                        if(!colinst.removeEmpresa((Empresa)inst))
                        {
                            throw new Exception("Empresa não cadastrada!");
                        }
                        else
                        {
                            System.out.println("Unidade desvinculada com sucesso!");
                        }
                    }
                }
                else
                {
                    System.out.print("Campus: ");
                    campus = input.nextLine();
                    inst = colinst.procuraInstEns(new InstituicaoEnsino(nome, cidade, campus));
                    System.out.print("Tem certeza que deseja desvincular o campus " + ((InstituicaoEnsino)inst).getCampus() + ", da cidade " + inst.getCidade() + " da instituição de ensino " + inst.getNome() + "? (S/N):");
                    String opcaoSN = input.nextLine();
                    if(opcaoSN.toLowerCase().equals("s"))
                    {
                        if(!colinst.removeInstEns((InstituicaoEnsino)inst))
                        {
                            throw new Exception("Instituição de ensino não cadastrada!");
                        }
                        else
                        {
                            System.out.println("Campus desvinculado com sucesso!");
                        }
                    }
                }
                return true;
        }
        return false;
    }

    private static boolean menuBlocos(Scanner input, ColecaoInstituicoes colinst) throws Exception
    {
        if(colinst.qtdIns() == 0)
        {
            throw new Exception("Ainda não há instituições cadastradas!");
        }
        String nome;
        String cidade;
        Instituicao inst = null;
        Bloco bloco;
        System.out.println("Escolha uma das opções abaixo:\n" +
                           "1 - Adicionar Bloco\n" +
                           "2 - Listar Blocos\n" +
                           "3 - Remover Bloco\n" +
                           "4 - Retornar ao Menu Anterior");
        switch(lerOpcao(input,1,4))
        {
            case 1:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                System.out.print("Bloco a ser adicionado: ");
                nome = input.nextLine();
                bloco = new Bloco(nome);
                if(!inst.getColblo().adicionaBloco(bloco))
                {
                    throw new Exception("Este bloco já havia sido cadastrado!");
                }
                System.out.println("Bloco adicionado com sucesso!");
                return true;
            case 2:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                inst.getColblo().listagemBlocos();
                return true;
            case 3:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco a ser desvinculado: ");
                nome = input.nextLine();
                bloco = new Bloco(nome);
                if(!inst.getColblo().removeBloco(bloco))
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                System.out.println("Bloco desvinculado com sucesso!");
                return true;
        }
        return false;
    }

    private static boolean menuSalas(Scanner input, ColecaoInstituicoes colinst) throws Exception
    {
        if(colinst.qtdIns() == 0)
        {
            throw new Exception("Ainda não há instituições cadastradas!");
        }
        String nome;
        String cidade;
        Instituicao inst = null;
        Bloco bloco;
        Sala sala;
        System.out.println("Escolha uma das opções abaixo:\n" +
                           "1 - Adicionar Sala\n" +
                           "2 - Listar Salas\n" +
                           "3 - Remover Sala\n" +
                           "4 - Retornar ao Menu Anterior");
        switch(lerOpcao(input,1,4))
        {
            case 1:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                System.out.print("Sala a ser adicionada: ");
                nome = input.nextLine();
                sala = new Sala(nome);
                if(!bloco.getColsal().adicionaSala(sala))
                {
                    throw new Exception("Esta sala já havia sido cadastrada!");
                }
                System.out.println("Sala cadastrada com sucesso!");
                return true;
            case 2:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                if(bloco.getColsal().qtdSal() == 0)
                {
                    throw new Exception("Ainda não há salas cadastradas nesse bloco!");
                }
                bloco.getColsal().listagemSalas();
                return true;
            case 3:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                if(bloco.getColsal().qtdSal() == 0)
                {
                    throw new Exception("Ainda não há salas cadastradas nesse bloco!");
                }
                System.out.print("Sala a ser desvinculada: ");
                nome = input.nextLine();
                sala = new Sala(nome);
                if(!bloco.getColsal().removeSala(sala))
                {
                    throw new Exception("Sala não cadastrada!");
                }
                System.out.println("Sala desvinculada com sucesso!");
                return true;
        }
        return false;
    }

    private static boolean menuDispositivos(Scanner input, ColecaoInstituicoes colinst) throws Exception
    {
        if(colinst.qtdIns() == 0)
        {
            throw new Exception("Ainda não há instituições cadastradas!");
        }
        String nome;
        String cidade;
        Instituicao inst = null;
        Bloco bloco;
        Sala sala;
        int qtdarc;
        int qtdpro;
        System.out.println("Escolha uma das opções abaixo:\n" +
                           "1 - Registrar Dispositivos de uma Sala\n" +
                           "2 - Remover todas as Máquinas de uma Sala\n" +
                           "3 - Remover todos os Condicionadores de Ar de uma Sala\n" +
                           "4 - Remover todos os Projetores de uma Sala\n" +
                           "5 - Retornar ao Menu Anterior");
        switch(lerOpcao(input,1,5))
        {
            case 1:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst, inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                if(bloco.getColsal().qtdSal() == 0)
                {
                    throw new Exception("Ainda não há salas cadastradas nesse bloco!");
                }
                System.out.print("Sala: ");
                nome = input.nextLine();
                sala = bloco.getColsal().pesquisaPeloNome(nome);
                if(sala == null)
                {
                    throw new Exception("Sala não cadastrada!");
                }
                System.out.println("Iniciado processo de registro de máquinas: ");
                try
                {
                    RegistraMaquinas regmaq = new RegistraMaquinas(colinst,sala.getColdis(),60500);
                    regmaq.start();
                }
                catch (Exception e)
                {
                    throw new Exception(e.getMessage());
                }
                System.out.println("Iniciado processo de registro de condicionadores de ar: ");
                System.out.print("Quantidade de condicionadores de ar da sala: ");
                qtdarc = lerOpcao(input,1,1000);
                for(int i = 0; i < qtdarc; i++)
                {
                    String ID = "" + sala.getNome() + i;
                    nome = "ARC" + sala.getNome() + ":" + i;
                    String endereco = "" + i + sala.getNome();
                    String tecnologia = "Infravermelho";
                    ArrayList<Comando> comandos = new ArrayList<Comando>();
                    Dispositivo arc = new ArCondicionado(ID, nome, endereco, tecnologia, comandos);
                    sala.getColdis().adicionaDispositivo(arc);
                }
                System.out.println("Iniciado processo de registro de projetores: ");
                System.out.print("Quantidade de projetores da sala: ");
                qtdpro = lerOpcao(input,1,1000);
                for(int i = 0; i < qtdpro; i++)
                {
                    String ID = "" + sala.getNome() + i;
                    nome = "PRO" + sala.getNome() + ":" + i;
                    String endereco = "" + i + sala.getNome();
                    String tecnologia = "Infravermelho";
                    ArrayList<Comando> comandos = new ArrayList<Comando>();
                    Dispositivo pro = new Projetor(ID, nome, endereco, tecnologia, comandos);
                    sala.getColdis().adicionaDispositivo(pro);
                }
                System.out.println("Dispositivos registrados!");
                return true;
            case 2:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                if(bloco.getColsal().qtdSal() == 0)
                {
                    throw new Exception("Ainda não há salas cadastradas nesse bloco!");
                }
                System.out.print("Sala: ");
                nome = input.nextLine();
                sala = bloco.getColsal().pesquisaPeloNome(nome);
                if(sala == null)
                {
                    throw new Exception("Sala não cadastrada!");
                }
                System.out.println("Apagando os registros de máquinas da sala " + sala.getNome() + "...");
                sala.getColdis().excluirMaquinas();
                System.out.println("Registros apagados com sucesso!");
                return true;
            case 3:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                if(bloco.getColsal().qtdSal() == 0)
                {
                    throw new Exception("Ainda não há salas cadastradas nesse bloco!");
                }
                System.out.print("Sala: ");
                nome = input.nextLine();
                sala = bloco.getColsal().pesquisaPeloNome(nome);
                if(sala == null)
                {
                    throw new Exception("Sala não cadastrada!");
                }
                System.out.println("Apagando os registros de condicionadores de ar da sala " + sala.getNome() + "...");
                sala.getColdis().excluirArCondicionados();
                System.out.println("Registros apagados com sucesso!");
                return true;
            case 4:
                System.out.println("Preencha os campos abaixo com os dados indicados:");
                System.out.print("Instituição: ");
                nome = input.nextLine();
                System.out.print("Cidade: ");
                cidade = input.nextLine();
                inst = colinst.procuraInst(new Instituicao(nome, cidade));
                inst = defineInst(input,nome,cidade,colinst,inst);
                if(inst == null)
                {
                    throw new Exception("Instituição não cadastrada!");
                }
                if(inst.getColblo().qtdBlo() == 0)
                {
                    if(inst instanceof Empresa)
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa empresa!");
                    }
                    else
                    {
                        throw new Exception("Ainda não há blocos cadastrados nessa instituição de ensino!");
                    }
                }
                System.out.print("Bloco: ");
                nome = input.nextLine();
                bloco = inst.getColblo().pesquisaPeloNome(nome);
                if(bloco == null)
                {
                    throw new Exception("Bloco não cadastrado!");
                }
                if(bloco.getColsal().qtdSal() == 0)
                {
                    throw new Exception("Ainda não há salas cadastradas nesse bloco!");
                }
                System.out.print("Sala: ");
                nome = input.nextLine();
                sala = bloco.getColsal().pesquisaPeloNome(nome);
                if(sala == null)
                {
                    throw new Exception("Sala não cadastrada!");
                }
                System.out.println("Apagando os registros de projetores da sala " + sala.getNome() + "...");
                sala.getColdis().excluirProjetores();
                System.out.println("Registros apagados com sucesso!");
                return true;
        }
        return false;
    }

    private static Instituicao defineInst(Scanner input, String nome, String cidade, ColecaoInstituicoes colinst, Instituicao inst)
    {
        String unidade;
        String campus;
        if(inst instanceof Empresa)
        {
            System.out.print("Unidade: ");
            unidade = input.nextLine();
            inst = colinst.procuraEmpresa(new Empresa(nome, cidade, unidade));
        }
        else
        {
            System.out.print("Campus: ");
            campus = input.nextLine();
            inst = colinst.procuraInstEns(new InstituicaoEnsino(nome, cidade, campus));
        }
        return inst;
    }

    private static int lerOpcao(Scanner input, int iniciall, int finall)
    {
        int opcao;
        if(!input.hasNextInt())
        {
            System.out.printf("Digite um número válido: \n");
            input.nextLine();
            return lerOpcao(input,iniciall,finall);
        }
        opcao = input.nextInt();
        input.nextLine();
        if(opcao < iniciall || opcao > finall)
        {
            System.out.printf("Digite um número entre '" + iniciall + "' e '" + finall + "' : \n");
            return lerOpcao(input,iniciall,finall);
        }
        return opcao;
    }
    
    public static void atualizaServidor(ColecaoInstituicoes colinst) throws Exception
    {
    	colinst.recuperArquivo();
        Socket clienteServ = null;
        ObjectInputStream oinServ = null;
        ObjectOutputStream ooutServ = null;
        clienteServ = new Socket("IP do Servidor",48000);
        oinServ = new ObjectInputStream(clienteServ.getInputStream());
        ooutServ = new ObjectOutputStream(clienteServ.getOutputStream());
        ooutServ.writeObject(new Stringo("SEND"));
        Instituicao inst = colinst.procuraInstEns((InstituicaoEnsino)(oinServ.readObject()));
        Bloco bloco = inst.getColblo().pesquisaPeloNome(((Bloco)oinServ.readObject()).getNome());
        Sala sala = bloco.getColsal().pesquisaPeloNome(((Sala)oinServ.readObject()).getNome());
        ColecaoDispositivos coldis = sala.getColdis();
        ooutServ.writeObject(coldis);
        oinServ.close();
        ooutServ.close();
        clienteServ.close();
    }
}
