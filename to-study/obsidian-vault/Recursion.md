
aggiungere link alla ricorsione visiva fatta con p5js

aggiungere una parte su ycombinator e come derivarla



spiegata ad un bambino delle elementari
ad un bambino delle medie
ad un ragazzo delle superiori
ad un ragazzo uscito dall'universita'
ad un professore



trampoline???





fold e reduce implementate come TAIL RECARS
fold non implementabile senza fuinzione d'appoggio per via dell'acc

ricorsione mutua


------------
let sideThresh=10; //soglia (threshold) per la dimensione minima del lato del quadrato
let probThresh=0.2 //soglia relativa alla probabilità di proseguire con la ricorsione

function setup() {
  createCanvas(400, 400);
  drawSquares(0,0,width);
}

function draw() {
}


function drawSquares(x,y,side){
  
  if(side > sideThresh && random()>probThresh ) {
      fill(random(256), random(256), random(256)); 
      rect(x,y, side,side);
      let hSide = side/2; 
      
      drawSquares(x,y, hSide);
      drawSquares(x+hSide, y, hSide);
      drawSquares(x, y+hSide, hSide);
      drawSquares(x+hSide, y+hSide, hSide);
    }
}
-------------
