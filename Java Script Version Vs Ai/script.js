const X_CLASS = "x"
const CIRCLE_CLASS = "circle"
const WINNING_COMBINATIONS = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6]
]
const playerPos = [];
const cpuPos = [];
const cellElements = document.querySelectorAll("[data-cell]")
const board = document.getElementById("board")
const winningMessageElement = document.getElementById("winningMessage")
const restartButton = document.getElementById("restartButton")
const winningMessageTextElement = document.querySelector("[data-winning-message-text]")
let circleTurn
startGame()
restartButton.addEventListener("click", startGame)
function startGame() {
  circleTurn = false
  cellElements.forEach(cell => {
    cell.classList.remove(X_CLASS)
    cell.classList.remove(CIRCLE_CLASS)
    cell.removeEventListener("click", handleClick)
    cell.addEventListener("click", handleClick, { once: true })
  })
  setBoardHoverClass()
  winningMessageElement.classList.remove("show")
}
function handleClick(e) {
  const cell = e.target
  const currentClass = circleTurn ? CIRCLE_CLASS : X_CLASS
  placeMark(cell, currentClass)
  if (checkWin(currentClass)) {
    endGame(false)
  } else if (isDraw()) {
    endGame(true)
  } else {
    swapTurns()
    setBoardHoverClass()
  }
}
function endGame(draw) {
  if (draw) {
    winningMessageTextElement.innerText = "Draw!"
  } else {
    winningMessageTextElement.innerText = `${circleTurn ? "O's" : "X's"} Wins!`
  }
  winningMessageElement.classList.add("show")
}
function isDraw() {
  return [...cellElements].every(cell => {
    return cell.classList.contains(X_CLASS) || cell.classList.contains(CIRCLE_CLASS)
  })
}
function placeMark(cell, currentClass) {
  cell.classList.add(currentClass)
}
function swapTurns() {
  circleTurn = !circleTurn
}
function setBoardHoverClass() {
  board.classList.remove(X_CLASS)
  board.classList.remove(CIRCLE_CLASS)
  if (circleTurn) {
    board.classList.add(CIRCLE_CLASS)
  } else {
    board.classList.add(X_CLASS)
  }
}
function checkWin(currentClass) {
  return WINNING_COMBINATIONS.some(combination => {
    return combination.every(index => {
      return cellElements[index].classList.contains(currentClass)
    })
  })
}
function secondAiMove(){
  switch(strat){
      case 1:
      if (playerPos.includes(2)||playerPos.includes(4) ||playerPos.includes(6) ||playerPos.includes(8)){
          boardState = 1;
          return 5;
      } else if (playerPos.includes(5)|| playerPos.includes(3)|| playerPos.includes(7)){
          boardState = 2;
          return 9;
      } else if (playerPos.includes(9)){
          boardState = 3;
          return 3;
      }
      break;
      case 2:
      if (playerPos.includes(2)||playerPos.includes(8)){
          boardState = 4;
          return 6;
      } else if (playerPos.includes(1)){
          boardState = 5;
          return 9;
      } else if (playerPos.includes(3)){
          boardState = 6;
          return 7;
      } else if (playerPos.includes(7)) {
          boardState = 7;
          return 3;
      } else if (playerPos.includes(9)){
          boardState = 8;
          return 1;
      } else if (playerPos.includes(4)||playerPos.includes(6)){
          boardState = 9;
          return 2;
      }
      break;
  }
  return -1;
}
function thirdAiMove() {
  switch(boardState){
      case 1:
      if(!playerPos.includes(9)){
          //win
          return 9;
      } else if (playerPos.includes(8)||playerPos.includes(2)){
          boardState = 1;
          return 7;
      } else if (playerPos.includes(6)||playerPos.includes(4)){
          boardState = 2;
          return 3;
      }
      break;
      case 2:
      if(!playerPos.includes(5)){
          //win
          return 5;
      } else if (playerPos.includes(3)) {
          boardState = 3;
          return 7;
      } else if (playerPos.includes(7)){
          boardState = 4;
          return 3;
      } else if (playerPos.includes(2)){
          boardState = 5;
          return 8;
      } else if (playerPos.includes(4)){
          boardState = 6;
          return 6;
      } else if (playerPos.includes(6)){
          boardState = 7;
          return 4;
      } else if (playerPos.includes(8)){
          boardState = 8;
          return 2;
      }
      break;
      case 3:
      if (!playerPos.includes(2)){
          //win
          return 2;
      } else {
          boardState = 9;
          return 5;
      }
      case 4:
      if (!playerPos.includes(4)){
          //win
          return 4;
      } else {
          boardState = 10;
          return 9;
      }
      case 5:
      if (playerPos.includes(2)){
          boardState = 11;
          return 3;
      } else if (playerPos.includes(3)){
          boardState = 12;
          return 2;
      } else if (playerPos.includes(4)){
          boardState = 13;
          return 7;
      } else if (playerPos.includes(6)){
          boardState = 14;
          return 8;
      } else if (playerPos.includes(7)){
          boardState = 15;
          return 4;
      } else if (playerPos.includes(8)){
          boardState = 16;
          return 6;
      }
      break;
      case 6:
      if (playerPos.includes(1)){
          boardState = 17;
          return 2;
      } else if (playerPos.includes(2)){
          boardState = 18;
          return 1;
      } else if (playerPos.includes(4)){
          boardState = 19;
          return 8;
      } else if (playerPos.includes(6)){
          boardState = 20;
          return 9;
      } else if (playerPos.includes(8)){
          boardState = 21;
          return 4;
      } else if (playerPos.includes(9)){
          boardState = 22;
          return 6;
      }
      break;
      case 7:
      if (playerPos.includes(1)){
          boardState = 23;
          return 4;
      } else if (playerPos.includes(2)){
          boardState = 24;
          return 6;
      } else if (playerPos.includes(4)){
          boardState = 25;
          return 1;
      } else if (playerPos.includes(6)){
          boardState = 26;
          return 2;
      } else if (playerPos.includes(8)){
          boardState = 27;
          return 9;
      } else if (playerPos.includes(9)){
          boardState = 28;
          return 8;
      }
      break;
      case 8:
      if (playerPos.includes(2)){
          boardState = 29;
          return 4;
      } else if (playerPos.includes(3)){
          boardState = 30;
          return 6;
      } else if (playerPos.includes(4)){
          boardState = 31;
          return 2;
      } else if (playerPos.includes(6)){
          boardState = 32;
          return 3;
      } else if (playerPos.includes(7)){
          boardState = 33;
          return 8;
      } else if (playerPos.includes(8)){
          boardState = 34;
          return 7;
      }
      break;
      case 9:
      if (!playerPos.includes(8)){
          //win
          return 8;
      } else {
          boardState = 35;
          return 1;
      }
  }
  return -1;
}
function fourthAiMove(){
  switch(boardState){
      case 1:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //win
          return 3;
      }
      case 2:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          //win
          return 7;
      }
      case 3:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //win
          return 8;
      }
      case 4:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          //win
          return 6;
      }
      case 5:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          boardState = 1;
          return 3;
      }
      case 6:
      if(!playerPos.includes(3)){
          //win
          return 3;
      } else {
          boardState = 2;
          return 7;
      }
      case 7:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          boardState = 3;
          return 3;
      }
      case 8:
      if(!playerPos.includes(3)){
          //win
          return 3;
      } else {
          boardState = 4;
          return 7;
      }
      case 9:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          boardState = 5;
          return 8;
      }
      case 10:
      if(!playerPos.includes(1)){
          //win
          return 1;
      } else {
          //win
          return 3;
      }
      case 11:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          //win
          return 6;
      }
      case 12:
      if(!playerPos.includes(8)){
          //win
          return 8;
      } else {
          boardState = 6;
          return 6;
      }
      case 13:
      if(!playerPos.includes(3)){
          //win
          return 3;
      } else {
          //win
          return 8;
      }
      case 14:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          //win
          return 2;
      }
      case 15:
      if(!playerPos.includes(6)){
          //win
          return 6;
      } else {
          boardState = 7;
          return 2;
      }
      case 16:
      if(!playerPos.includes(3)){
          //win
          return 3;
      } else {
          //win
          return 4;
      }
      case 17:
      if(!playerPos.includes(8)){
          //win
          return 8;
      } else {
          boardState = 8;
          return 6;
      }
      case 18:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //win
          return 9;
      }
      case 19:
      if(!playerPos.includes(9)){
          //win
          return 9;
      } else {
          //win
          return 2;
      }
      case 20:
      if(!playerPos.includes(8)){
          //win
          return 8;
      } else {
          //win
          return 2;
      }
      case 21:
      if(!playerPos.includes(1)){
          //win
          return 1;
      } else {
          //win
          return 6;
      }
      case 22:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          boardState = 9;
          return 2;
      }
      case 23:
      if(!playerPos.includes(6)){
          //win
          return 6;
      } else {
          boardState = 10;
          return 8;
      }
      case 24:
      if(!playerPos.includes(9)){
          //win
          return 9;
      } else {
          //win
          return 4;
      }
      case 25:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          //win
          return 9;
      }
      case 26:
      if(!playerPos.includes(1)){
          //win
          return 1;
      } else {
          //win
          return 8;
      }
      case 27:
      if(!playerPos.includes(6)){
          //win
          return 6;
      } else {
          //win
          return 1;
      }
      case 28:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          boardState = 11;
          return 4;
      }
      case 29:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          //win
          return 6;
      }
      case 30:
      if(!playerPos.includes(7)){
          //win
          return 7;
      } else {
          //win
          return 6;
      }
      case 31:
      if(!playerPos.includes(3)){
          //win
          return 3;
      } else {
          //win
          return 8;
      }
      case 32:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          //win
          return 7;
      }
      case 33:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          boardState = 12;
          return 6;
      }
      case 34:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //win
          return 3;
      }
      case 35:
      if(!playerPos.includes(3)){
          //win
          return 3;
      } else {
          //win
          return 9;
      }
  }
  return -1;
}

function fifthAiMove() {
  switch(boardState){
      case 1:
      if (!playerPos.includes(6)){
          //win
          return 6;
      } else {
          //draw
          return 4;
      }
      case 2:
      if (!playerPos.includes(8)){
          //win 
          return 8;
      } else{
          //draw
          return 2;
      }
      case 3:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          //draw
          return 8;
      }
      case 4:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //draw
          return 6;
      }
      case 5:
      if(!playerPos.includes(4)){
          //draw
          return 4;
      } else {
          //draw
          return 6;
      }
      case 6:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //draw
          return 7;
      }
      case 7:
      if(!playerPos.includes(8)){
          //win
          return 8;
      } else {
          //draw
          return 3;
      }
      case 8:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //draw
          return 9;
      }
      case 9:
      if(!playerPos.includes(8)){
          //win
          return 8;
      } else {
          //draw
          return 1;
      }
      case 10:
      if(!playerPos.includes(2)){
          //win
          return 2;
      } else {
          //draw
          return 9;
      }
      case 11:
      if(!playerPos.includes(6)){
          //win
          return 6;
      } else {
          //draw
          return 1;
      }
      case 12:
      if(!playerPos.includes(4)){
          //win
          return 4;
      } else {
          //draw
          return 3;
      }
  }
  return -1;
}
