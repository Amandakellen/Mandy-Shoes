
# Mandy Shoes  
**Projeto: Developing First Android App - Udacity**  

## Descrição  
Mandy Shoes é um aplicativo multi-tela desenvolvido em Android utilizando **Single Activity Architecture**, **ViewModel**, **LiveData** e **DataBinding**. O projeto foi estruturado seguindo as boas práticas do Android, com foco em qualidade de código, layouts otimizados e navegação eficiente.  

## Funcionalidades Principais  
- **Login e Navegação**: O app permite ao usuário navegar por cinco telas principais:  
  1. **Login**  
  2. **Welcome**  
  3. **Instructions**  
  4. **Listing**  
  5. **Detail**  
- **Gerenciamento de Dados**: Utilização de **ViewModel** e **LiveData** para compartilhar dados entre telas, garantindo que novas entradas na tela de detalhe sejam exibidas na tela de listagem.  
- **DataBinding**: Implementação de **DataBinding** para exibir e salvar dados nos layouts de forma reativa.  
- **Logout Menu**: Disponível na tela de listagem, permitindo retornar à tela de Login.  

## Estrutura do Projeto  
O projeto utiliza **Single Activity Architecture**, com **MainActivity** servindo como contêiner para os **Fragments**.   

## Requisitos Técnicos  
### Code Quality  
- Uso correto das classes de ciclo de vida **ViewModel** e **LiveData**.  
- Código livre de erros e organizado.  
- Implementação de **Single Activity Architecture** com **MainActivity** e múltiplos **Fragments**.  

### Layouts  
- **LinearLayout** e **ConstraintLayout** utilizados de maneira adequada.  
- **DataBinding** configurado em todos os layouts.  
- Layout otimizado para evitar **ViewGroups** aninhados.  
- Uso correto do atributo `@={}` no **DataBinding** para vincular dados a campos de texto.  

### Navegação  
- **Arquivo de navegação** configurado com todas as rotas necessárias e ações definidas.  
- Uso do **NavController** para gerenciar a navegação entre as telas.  
- Implementação do botão de retorno funcional.  
- Menu de logout configurado na tela de listagem para redirecionar à tela de Login.  

## Requisitos de Estilo  
- Estilização aplicada em **TextViews** e botões para uma interface amigável.  
- **Nomes capitalizados** utilizados no arquivo de navegação.  
- Animações de entrada e saída configuradas durante a navegação entre telas.  

## Tecnologias Utilizadas  
- **Kotlin**  
- **Android Jetpack** (ViewModel, LiveData, Navigation, DataBinding)  
- **ConstraintLayout** e **LinearLayout**  
- **Single Activity Architecture**  

## Como Executar o Projeto  
1. Clone este repositório:  
   ```bash
   git clone https://github.com/seu-usuario/mandy-shoes.git
   ```  
2. Abra o projeto no Android Studio.  
3. Compile e execute no emulador ou dispositivo físico.  


## Conclusão  
Mandy Shoes demonstra habilidades essenciais no desenvolvimento de aplicativos Android, incluindo boas práticas de arquitetura, qualidade de código e otimização de layouts. O projeto é uma base sólida para criação de aplicações robustas e escaláveis.  
