#!/bin/sh

echo "👋 starting cluster 🚀"
eval $(cat cluster.config)

multipass start ${node1_name}
#multipass start ${node2_name}
#multipass start ${node3_name}

multipass list

