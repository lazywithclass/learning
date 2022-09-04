# Learning to teach JS

## Arkanoid

### Guidelines

Follows a list of steps to help me live code the game.

 * written disclaimer about how I will write the code
 * HTML basic structure
   * remember to use live.js
   * canvas and CSS
   ```
   html, body {
     height: 100%;
     margin: 0;
   }
       
   body {
     background: black;
     display: flex;
     align-items: center;
     justify-content: center;
   }
    
   canvas {
     border: 1px solid white;
   }
   ``` 
 * JS basic structure
   * game loop using `requestAnimationFramea`, show how it works
   * platform
     * create
     * draw
     * move first allowing it to cross the left / right borders
     * collision with borders
   * ball 
     * create
     * draw 
     * collision with borders and change of direction
   * bricks
     * create
     * draw
     * destroy and remove once hit
   
 ### Extras
 
 Introduce OOP.
 
 Websockets.
