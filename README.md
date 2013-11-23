Mixturis
========

A simple custom recipe plugin for Bukkit servers

Mixturis is Latin for 'Recipes'

##Configuring recipes

To add new custom recipes, you need to edit _recipes.yml_

###Shaped recipes

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

All shaped recipes should be entered in the `shaped:` configuraion section.

* `Vorpal Sword:` is the name of the recipe and the resulting crafted item (if `displayname` is set to `true`)
* `shape` is how the items are placed in the crafting grid. The order is by rows (top to bottom) - the commas separate the rows. If the crafting slot should be empty type a dash (`-`), otherwise enter an uppercase letter for each __different item type__
* the `ingredients` section defines the item IDs and data that are represented in the `shape` key.
   * The letters in this section should correspond to the placeholders that were entered into `shape`.
   * the values should be the id (and optional data values) of the item that is to be used
   * if data values are included, the values should be separated with a colon (`:`) and must be wrapped in single quotes - `'ID:data'` for example: `'5:2'`
* `result` is the id (and optional data value) of the item that will get crafted
* `amount` is how many of the crafted item the player will get from this recipe
* `displayname` determines whether to add a custom display name for the crafted item - if `true` then the recipe name is used
* `lore` if the item should display custom lore, you can add it here (it will only be added if `displayname` is set to `true`)
   * to add multiple lines of lore, separate them with `/n`
   * Use an empty string `""` to have a display name but no lore

The shaped recipe example above will craft a diamond sword called "Vorpal Sword" with the lore (on two lines) "The vorpal blade goes snicker-snack!". The recipe is shown in the crafting grid.

![screen shot](https://github.com/eccentricdevotion/Mixturis/blob/master/src/images/vorpalsword.jpg?raw=true "Vorpal Sword")

###Shapeless recipes

Example:

```
shapeless:
    Slime:
        recipe: 106,106,106
        result: 341
        amount: 1
        displayname: false
        lore: ""
```

All shapeless recipes should be entered in the `shapeless:` configuraion section.


* `Slime:` is the name of the recipe and the resulting crafted item (if `displayname` is set to `true`)
* `recipe` is a simple comma separated list of item IDs (and optional data values)
* `result`, `amount`, `displayname`, and `lore` are the same as for __Shaped recipes__ above

The shapeless recipe example above will craft a slimeball without a custom name and lore. The recipe is three VINE in any crafting slot.

![screen shot](https://github.com/eccentricdevotion/Mixturis/blob/master/src/images/slime.jpg?raw=true "Slimeball")

###Furnace recipes

Example:

```
furnace:
    Nuclear Wool:
        recipe: '35:0'
        result: '35:15'
        displayname: true
        lore: "Done to a crisp"
```

All furnace recipes should be entered in the `furnace:` configuraion section.

* `Nuclear Wool:` is the name of the recipe and the resulting smelted item (if `displayname` is set to `true`)
* `recipe` is a the item ID (and optional data value) of the block to be smelted
* `result`, `displayname`, and `lore` are the same as for __Shaped recipes__ above

The furnace recipe example above will smelt WHITE WOOL into BLACK WOOL  with the name "Nuclear Wool" and lore "Done to a crisp".

![screen shot](https://github.com/eccentricdevotion/Mixturis/blob/master/src/images/nuclearwool.jpg?raw=true "Nuclear Wool")


