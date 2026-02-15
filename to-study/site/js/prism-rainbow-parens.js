let level = 0

function isLisp(lang) {
  return lang === "clojure" || lang === "scheme" || lang === "lisp";
}

Prism.hooks.add('wrap', function (env) {
  if (isLisp(env.language) && env.type == "punctuation") {
    if (env.content == "(" || env.content == "[" || env.content == "{") {
      level++;
      env.classes.push("rbl" + level);
    }
    if (env.content == ")" || env.content == "]" || env.content == "}") {
      env.classes.push("rbl" + level);
      level--;
    }
  }
});
