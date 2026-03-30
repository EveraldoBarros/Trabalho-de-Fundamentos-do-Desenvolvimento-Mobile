# Trabalho de Fundamentos do Desenvolvimento Mobile

Este repositório contém um projeto Android em Kotlin para um aplicativo de lista de tarefas com login e armazenamento em nuvem via Firebase.

## O que está incluído
- Tela de login
- Tela de cadastro
- Tela principal com RecyclerView de tarefas
- Tela de criação/edição de tarefa
- Firebase Authentication
- Firestore para salvar tarefas na nuvem

## Como abrir o projeto
1. Abra o Android Studio.
2. Selecione "Open" e escolha a pasta deste repositório.
3. Aguarde a sincronização do Gradle.

## Configuração do Firebase
1. No Android Studio, abra `Tools > Firebase`.
2. Adicione `Authentication` com Email e senha.
3. Adicione `Firestore Database` em modo de teste.
4. Baixe o arquivo `google-services.json` do Firebase e coloque em `app/`.

## Observações
- O projeto utiliza `com.google.firebase:firebase-auth-ktx` e `firebase-firestore-ktx`.
- Se necessário, o Android Studio pode gerar o Gradle wrapper e baixar dependências.
