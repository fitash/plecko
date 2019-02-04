/plecko
core domain

/hoard
rss feeds -> akka actors -> items:Topic

/store
items:Topic-> kafka stream -> redis

/search
items:Topic ->kafka stream -> lucene index