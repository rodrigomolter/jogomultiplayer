# Jogo Multiplayer: Versão Servidor

Desenvolvimento de um jogo **multiplayer** em JAVA com uma comunicação cliente-servidor, onde tem o propósito de aprender sobre comunicação, sincronização e tolerância a falhas em sistemas distribuídos.

O trabalho foi feito utilizando de dois projetos: CLIENTE e SERVIDOR. Ambos são projetos separados, porém não independentes.

O jogo se baseia em um jogo de luta de rua, onde os adversários se enfrentam todos juntos em uma "arena".
O propósito é estudar conceitos de detecção de colisão entre dois objetos e a comunicação dessa colisão a todos os participantes.
Também em atualizar em tempo-real sobre as mudanças de estado dos personagens, como: posição, quantidade de vida, status (andando, parado, atacando).

A lógica fundamental por tras de tudo isso é a comunicação e troca de dados entre os dois.
O CLIENTE apenas exibe os dados que o SERVIDOR informa, ou seja, todo o CONTEXTO (posição, vida, etc.) fica guardado e é alterada SOMENTE no SERVIDOR.
<br>O grande desafio para o desenvolvimento desse projeto foi manter todos os clientes conectados com o CONTEXTO igual ou muito semelhante, por mais que o nome seja cliente-servidor, esse jogo não se baseia em uma metodologia requisição-resposta.

O servidor pode muito bem enviar informações ao cliente, mesmo que o mesmo não tenha requisitado. 
Isso é essencial, pois não há como o cliente saber quando haverá movimentação de um outro jogador.


#### Pequena demonstração da execução, enquanto ainda em desenvolvimento:
![](https://github.com/rodrigomolter/jogomultiplayer-servidor/blob/main/runtime.gif) 

## Arquitetura
O desenvolvimento foi feito utlizando de `Threads`, onde cada novo cliente que se conectar ao servidor é uma thread diferente, tratando de cada usuário paralelamente.

Atualmente, a única informação que o cliente manda para o servidor é a tecla pressionada pelo usuário.
Essa tecla vem em formato de `keyCode`, onde cada tecla tem um número único. Ex: A tecla enter corresponde ao número 10.

A resposta ao cliente segue o seguinte padrão: 
```
"AÇÃO, ID, VIDA, X, Y, APARENCIA"
```
Essa resposta é replicada a todos os clientes que estão conectados, garantindo que todos tenham o mesmo contexto.

## Interface Gráfica
A interface gráfica e a atualização dos componentes, como animação dos personagens, barra de vida, etc. foi feita utilizando do [Processing](https://processing.org/).

> O Processing é voltado para a criação de projetos interativos, especialmente em contextos de arte, design, visualização de dados e educação. Construido em cima da linguagem java tem como objetivo facilitar a criação de conteúdo visual e interativo, permitindo que os programadores se concentrem mais na expressão criativa do que na complexidade técnica. Ele simplifica muitas tarefas comuns de programação gráfica, como desenho de formas, manipulação de imagens e animações.

## Eai, Acertei? - Detecção de Colisão

Em muitos sistemas de jogos e simulações, os personagens e objetos são frequentemente tratados como retângulos para simplificar a detecção de colisões. Um retângulo invisível em volta dos personagens os acompanha para ajudar a identificar com mais precisão e agilidade caso haja uma colisão.

A razão para isso é a facilidade de cálculo e implementação. Tratar personagens como retângulos simplifica a detecção de colisões, pois os retângulos podem ser representados por coordenadas de posição (por exemplo, posX, posY) e dimensões (largura e altura).<br>

O algoritmo verifica se os lados direito e esquerdo do retângulo atual estão dentro dos limites do outro retângulo em questão. Além disso, verifica se a parte inferior e superior do retângulo atual se sobrepõem com o outro retângulo. Se todas as condições forem atendidas, é considerada uma colisão e uma ação, como a aplicação de dano, pode ser tomada. Este algoritmo é útil em jogos e simulações onde é necessário detectar interações entre objetos retangulares.

No nosso caso, essa verificação é feita toda vez que algum personagem usa o comando de atacar. Então, é necessário verificar a colisão com todos os personagens conectados.
```
if (posX + detectionA < p.getPosX() + p.getWidth() &&
           posX + width + detectionB > p.getPosX() &&
           posY < p.getPosY() + p.getHeight() &&
           height + (posY) > p.getPosY() ) {

   p.levouDano();
   return true;
}
```

## Cliente
Encontre a [versão Cliente desse projeto aqui](https://github.com/rodrigomolter/jogomultiplayer-cliente)
