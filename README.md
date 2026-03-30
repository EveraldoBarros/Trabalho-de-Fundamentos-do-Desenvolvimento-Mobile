# Trabalho de Fundamentos do Desenvolvimento Mobile

Este repositório contém um projeto Android em Kotlin para um aplicativo de lista de tarefas com login e armazenamento em nuvem via Firebase.

## Pré-requisitos
- Android Studio instalado (recomendado Android Studio Electric Eel ou superior)
- JDK 17 configurado
- Conexão de internet para baixar dependências Gradle
- Projeto configurado com `google-services.json` em `app/`

## Configuração do Firebase
1. Crie um projeto no Firebase.
2. Ative `Authentication` com o método Email/senha.
3. Crie um banco de dados `Firestore` em modo de teste.
4. Adicione seu aplicativo Android no Firebase.
5. Baixe o arquivo `google-services.json` e copie para `app/`.

## Como abrir o projeto
1. Abra o Android Studio.
2. Selecione `Open` e escolha a pasta deste repositório.
3. Aguarde a sincronização do Gradle e a indexação do projeto.

## Como executar o aplicativo
1. Conecte um dispositivo Android ou inicie um emulador.
2. No Android Studio, selecione o módulo `app` como alvo de execução.
3. Clique em `Run` (ícone de play) ou use `Shift + F10`.
4. O app será instalado e aberto no dispositivo/emulador.

## Funcionalidades do aplicativo
- Login com email e senha
- Cadastro de novos usuários
- Lista de tarefas do usuário conectada ao Firestore
- Criação, edição e exclusão de tarefas

## Uso do app
### 1. Login/Cadastro
- Na primeira tela, insira email e senha para fazer login.
- Se não tiver conta, use a opção de cadastro para registrar um novo usuário.

### 2. Visualizar tarefas
- Após o login, o app mostra a lista de tarefas do usuário.
- As tarefas são exibidas em um `RecyclerView` com título e descrição.

### 3. Criar tarefa
- Toque no botão de nova tarefa para abrir o formulário de criação.
- Insira título e descrição.
- Salve para enviar a tarefa ao Firestore.

### 4. Editar tarefa
- Selecione uma tarefa existente na lista.
- Altere o título ou a descrição no formulário de edição.
- Salve para atualizar a tarefa no Firestore.

### 5. Excluir tarefa
- Abra a tarefa que deseja remover.
- Use o botão de exclusão no formulário ou ação de excluir disponível.
- Confirme para remover a tarefa do Firestore.

## Dependências principais
- `androidx.core:core-ktx`
- `androidx.appcompat:appcompat`
- `com.google.android.material:material`
- `androidx.constraintlayout:constraintlayout`
- `androidx.recyclerview:recyclerview`
- `com.google.firebase:firebase-auth-ktx`
- `com.google.firebase:firebase-firestore-ktx`

## Observações
- Caso o Gradle não sincronize, execute `File > Sync Project with Gradle Files` no Android Studio.
- Verifique se o `google-services.json` está em `app/` e se corresponde ao pacote `com.taskflow.todoapp`.
