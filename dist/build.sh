#!/usr/bin/env bash

set -e
set -x

conda env create -n beakerx --file configuration.yml

. /opt/conda/etc/profile.d/conda.sh

conda activate beakerx
pip install -r requirements.txt --verbose
beakerx_kernel_groovy install

conda deactivate
jupyter kernelspec install --sys-prefix --replace --name groovy /opt/conda/envs/beakerx/share/jupyter/kernels/groovy