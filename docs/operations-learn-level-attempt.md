# Entity Operations Checklist

## Entity name: LearnLevelAttempt

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

### Query: Get the highest difficulty attempt

Purpose

: This query returns the highest difficulty ranking out of all of the challenge attempts (this difficulty ranking may be 0, if the user has not beat any levels).  

Cardinality/modality

: one/required
 
Filter

: Filter by the player.
 
Sort order

: Sort by difficulty in descending order and limit to one result.

### Query: Get the timestamp of the highest difficulty attempt

Purpose

: This query returns the timestamp of the associated with the highest difficulty ranking acheived in an attempt. If there are multiple attempts with the same ranking, it returns the most recent.  

Cardinality/modality

: one/optional
 
Filter

: Filter by the player and highest difficulty level.
 
Sort order

: Sort by Date descending and limit to one.