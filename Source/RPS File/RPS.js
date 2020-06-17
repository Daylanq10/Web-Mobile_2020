
var User = prompt("rock, paper, or scissors");
var Comp = Math.random();

document.write("<p>The User chose " + User + "</p>")

if (Comp < 0.33) {Comp = 'rock';}
else if (Comp <= 0.66) {Comp = 'paper';}
else {Comp = 'scissors';}

document.write("<p>The Computer chose " + Comp + "</p>");

var game = function(choice1, choice2) {
    if(choice1 === choice2){
        return"Its a tie!";
    }

    if(choice1 === 'rock'){
        if(choice2 === 'paper'){
            return"Paper wins!";
        }
        else{
            return "Rock win!";
        }
    }

    if(choice1 === 'paper'){
        if(choice2 === 'rock'){
            return "Paper wins!";
        }
        else{
            return "Scissors win!";
        }
    }
    if(choice1 === 'scissors'){
        if(choice2 === 'paper'){
            return "Scissors win!";
        }
        else{
            return "Rock wins!";
        }
    }
};

var result = game(User, Comp)
document.write("<p>" + result +"</p>")
var it = Users();