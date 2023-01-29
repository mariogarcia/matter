FROM jupyter/base-notebook as build
USER root
COPY . /tmp/matter/
WORKDIR /tmp/matter/
RUN apt-get update && apt-get install -y openjdk-11-jdk-headless
RUN chmod +x build.sh
RUN ./build.sh
#RUN /bin/bash -c "conda env create -n beakerx --file configuration.yml && . /opt/conda/etc/profile.d/conda.sh && conda activate beakerx && pip install -r requirements.txt --verbose && beakerx_kernel_groovy install"
#RUN /bin/bash -c "conda env create -n beakerx --file configuration.yml && . /opt/conda/etc/profile.d/conda.sh && conda activate beakerx && conda install conda-build && conda build . && conda install beakerx_kernel_groovy"
USER root
RUN fix-permissions /etc/jupyter/
#RUN fix-permissions /opt/conda/share/jupyter/kernels/matter
RUN fix-permissions /home/jovyan/.local/share/jupyter
#USER jovyan
WORKDIR $HOME