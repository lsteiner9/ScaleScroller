# Entity Operations Checklist

## Entity name: ChallengeAttempt

## Operations

In the list of operations below, check all the operations that apply. For example, if you know you will need to be able to insert a single instance of the entity at a time into the database, check **Single instance** in the **Create/insert** section.

Note that the pairs of square brackets below are rendered as checkboxes in GitHub Pages. To insert a checkmark, **replace** the single space between the square brackets in the Markdown with an "x" character (uppercase or lowercase; **do not** include the quote characters). To remove a checkmark, **replace** the "x" between the square brackets with a **single** space character. Aside from adding or removing checkmarks, do not modify the formatting or content of the remainder of this section.

### Create/insert
    
* [x] Single instance 
* [ ] Multiple instances 
    
### Read/query/select

* [x] Single instance 
* [ ] Multiple instances 

### Update

* [ ] Single instance 
* [ ] Multiple instances 

### Delete

* [x] Single instance 
* [x] Multiple instances 


## Queries

For any queries (i.e. selecting from the database) that you think you will need to do with this entity, summarize the purpose of the query (what functionality in your application will use the query), whether the query is intended to return a single instance or multiple instances (and whether returning no instances is a valid possibility), what field/column of your entity will be used as filter criteria, and in what order the results (if multiple) should be returned.

Copy and paste the section below as many times as necessary, for all of the queries you currently anticipate implementing for this entity.

### Query: Get the highest scores and their timestamps.

Purpose

: Returns a list of the 10 highest scores along with their timestamps for the selected player.

Cardinality/modality

: many/optional
 
Filter

: Filter by the player. 
 
Sort order

: Sort by highest score descending, limited to 10.


### Query: Get the highest scores on the device, with player names and timestamps. 

Purpose

: Returns a list of the 10 highest scores on the device.

Cardinality/modality

: many/optional
 
Filter

: No filter. 
 
Sort order

: Sort by highest score descending, limited to 10.
