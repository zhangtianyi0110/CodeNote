# Mustanche语法

## 1.	`demo`

> 测试用例

```html
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mustanche语法</title>
</head>
<body></body>
<script src="https://cdn.bootcss.com/mustache.js/2.3.0/mustache.js"></script>
<script type="text/javascript">
  var data = {
    "name": "zty",
    "msg": {
      "sex": "male",
      "age": "26",  
      "hobit": "coding"
    },
    "code_language": ["Ch", "En", "Math", "physics"]
  }
  var template = '<p>{{name}}</p>';
  var html = Mustache.render(template, data);
  console.log(html);
</script>
</html> 
```
---
## 2. 	`{{data}}`

> `{{}}`就是 Mustache 的标示符，花括号里的 data 表示键名，这句的作用是直接输出与键名匹配的键值，例如：{{name}}

```javascript
var template = '<p>{{name}}</p>';
var html = Mustache.render(template, data);
console.log(html);
// 输出
<p>zty</p>
```

---

## 3.	`{{#data}} {{/data}}`

> ​	以`#`开始、以`/`结束表示区块，它会根据当前上下文中的键值来对区块进行一次或多次渲染，例如改写下 Demo 中的 tpl：

```javascript
var template = '{{#msg}} <p>{{sex}},{{age}},{{hobit}}</p> {{/msg}}';
var html = Mustache.render(template, data);
console.log(html);
// 输出
<p>male,26,coding</p>
```
> 注意：如果{{#data}} {{/data}}中的 data 值为 null, undefined, false；则不渲染输出任何内容。
---

## 4.	`{{^data}} {{/data}}`	

> 该语法与`{{#data}} {{/data}}`类似，不同在于它是当 data值为 null, undefined, false 时才渲染输出该区块内容。

```javascript
var template = "{{^notkey}}没找到 notkey 键名就会渲染这段{{/notkey}}";
var html = Mustache.render(template, data);
console.log(html);
// 输出
没找到 notkey 键名就会渲染这段
```

---

## 5.	`{{.}}`

> `{{.}}`表示枚举，可以循环输出整个数组，例如：

```javascript
var template = '<ul>{{#code_language}} <li>{{.}}</li> {{/code_language}}</ul>';
var html = Mustache.render(template, data);
console.log(html);
// 输出
<ul> <li>Ch</li>  <li>En</li>  <li>Math</li>  <li>physics</li> </ul>
```

---

## 6.	`{{>partials}}`

> 以`>`开始表示子模块，如{{> msg}}；当结构比较复杂时，我们可以使用该语法将复杂的结构拆分成几个小的子模块，例如：

```javascript
var template = "<h1>{{name}}</h1> <ul>{{>msg}}</ul>"
var partials = {
msg: "{{#msg}}<li>{{sex}}</li><li>{{age}}</li><li>{{hobit}}</li>{{/msg}}"
}
var html = Mustache.render(template, data, partials);
console.log(html);
// 输出
<h1>zty</h1> <ul><li>male</li><li>26</li><li>coding</li></ul>
```

---

## 7.	`{{{data}}}`

> `{{data}}`输出会将等特殊字符转译，如果想保持内容原样输出可以使用`{{{}}}`，例如：

```javascript
data.msg.age = '26>'
var template = '{{#msg}} <p>{{{age}}}</p> {{/msg}}'
var html = Mustache.render(template, data, partials);
console.log(html);
// 输出
<p>26></p> 
```

> `>`符号被直接输出了，没有被转义成`&gt;`。

---

## 8.	`{{!comments}}`

> `!`表示注释，注释后不会渲染输出任何内容。

```javascript
var template = '{{!注释不显示}},name is {{name}}'
var html = Mustache.render(template, data);
console.log(html);
// 输出
,name is zty
```

