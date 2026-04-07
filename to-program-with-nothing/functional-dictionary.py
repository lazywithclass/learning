def put(k, v, m):
    return lambda key: v if k == key else m(key)

def get(k, m):
    return m(k)
    
themap = lambda k: None


themap = put("k1", "v1", themap)
themap = put("k2", "v2", themap)
themap = put("k3", "v3", themap)

print(get("k3", themap)) # v3
print(get("k1", themap)) # v1
print(get("k2", themap)) # v2
print(get("no", themap)) # None

