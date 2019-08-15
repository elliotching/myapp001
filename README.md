# myapp001
## Task 3

### Dagger 2 Injection used on:
1. NetworkHelperComponent
2. RecyclerViewPresenterComponent

### Organized according 
All init progress using integer increment for the id Primary Key.

### AsyncTask w/ realm.executeTransaction
All realm operation has been executed in background thread using AsyncTask.
Inside background thread, realm operation been done using realm.executeTransaction()

#### Picasso
checked before saved on the image link, created a default link if empty link
On image link EditText focus lost, a default image included immediately ("//picsum.photos/id/0/5616/3744") into text view if empty (library:Picasso doesn't allowed empty string)


###### P/S: currently all pre-defined images for each items are same
