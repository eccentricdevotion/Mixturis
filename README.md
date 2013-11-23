Mixturis
========

A simple custom recipe plugin for Bukkit servers

Mixturis is Latin for 'Recipes'

##Configuring recipes

####Shaped recipes

Example:

```
shaped:
    Vorpal Sword:
        shape: A-A,BBB,CDC
        ingredients:
            A: 1
            B: 2
            C: '5:2'
            D: 57
        result: 276
        amount: 1
        displayname: true
        lore: "The vorpal blade\ngoes snicker-snack!"
```

All shaped recipes should be entered in the `shaped` configuraion section.

* `Vorpal Sword:` is the name of the recipe and the resulting crafted item (if `displayname` is set to `true`)
* `shape` is how the items are placed in the crafting grid. The order is by rows (top to bottom) - the commas separate the rows. If the crafting slot should be empty type a dash (`-`), otherwise enter an uppercase letter for each different __item type__
* the `ingredients` section defines the item IDs and data that are represented in the `shape` key.
   * The letters in this section should correspond to the placeholders that were entered into `shape`.
   * the values should be the id (and optional data values) of the item that is to be used
   * if data values are included, the value must be wrapped in single quotes - `'5:2'`
* `result` is the id (and optional data value) of the item that will get crafted
* `amount` is how many of the crafted item the player will get from this recipe
* `displayname` determines whether to add a custom display name for the crafted item - if `true` then the recipe name is used
* `lore` if the item should display custom lore, you can add it here (it will only be added if `displayname` is set to `true`)
   * to add multiple lines of lore, separate them with `/n`
   * Use an empty string `""` to have a display name but no lore

The shaped recipe example above will craft a diamond sword called "Vorpal Sword" with the lore (on two lines) "The vorpal blade goes snicker-snack!"

![screen shot](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Vorpal Sword")


