#!/bin/sh
eval $(cat cluster.config)

multipass launch -n ${node1_name} --cpus 2 --mem 2G
multipass launch -n ${node2_name} --cpus 2 --mem 2G
multipass launch -n ${node3_name} --cpus 2 --mem 2G

# Initialize K3s on node1
echo "👋 Initialize 📦 K3s on ${node1_name}..."

multipass --verbose exec ${node1_name} -- sh -c "
  curl -sfL https://get.k3s.io | sh -
"

TOKEN=$(multipass exec ${node1_name} sudo cat /var/lib/rancher/k3s/server/node-token)
IP=$(multipass info ${node1_name} | grep IPv4 | awk '{print $2}')

echo "😃 📦 K3s initialized on ${node1_name} ✅"
echo "🤫 Token: ${TOKEN}"
echo "🖥 IP: ${IP}"

# Join node2 and node3 to the Cluster
echo "👋 Join ${node2_name} and ${node3_name} to the Cluster"

# Joining node2
multipass --verbose exec ${node2_name} -- sh -c "
  curl -sfL https://get.k3s.io | K3S_URL='https://$IP:6443' K3S_TOKEN='$TOKEN' sh -
"

echo "😃 ${node2_name} has joined the Cluster  ✅"

# Joining node3
multipass --verbose exec ${node3_name} -- sh -c "
  curl -sfL https://get.k3s.io | K3S_URL='https://$IP:6443' K3S_TOKEN='$TOKEN' sh -
"

echo "😃 ${node3_name} has joined the Cluster  ✅"


multipass exec ${node1_name} sudo cat /etc/rancher/k3s/k3s.yaml > k3s.yaml

sed -i  "s/127.0.0.1/$IP/" k3s.yaml
