#!/bin/sh

echo "👋 stoping cluster 🥱"
eval $(cat cluster.config)

multipass stop ${node1_name}
multipass stop ${node2_name}
multipass stop ${node3_name}

multipass list

