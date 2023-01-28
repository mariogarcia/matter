FROM jupyter/base-notebook
USER root
#RUN apt-get update && apt-get install -y openjdk-11-jre
#COPY ./base-dependencies/ /root/.m2/repository/
COPY ./ /tmp/gk/
WORKDIR /tmp/gk/groovy-dist

#RUN conda install conda-build
#RUN /bin/bash -c "conda-build conda"

RUN /bin/bash -c "conda env create -n beakerx -f configuration.yml && . /opt/conda/etc/profile.d/conda.sh && conda activate beakerx && pip install -r requirements.txt --verbose && beakerx_kernel_groovy install --lab"
#RUN /bin/bash -c "pip install -r requirements.txt --verbose && beakerx_kernel_groovy install --lab"
# Fix permissions as root
USER root
RUN fix-permissions /etc/jupyter/
RUN fix-permissions /opt/conda/envs/beakerx/share/jupyter/kernels/groovy
RUN fix-permissions /home/jovyan/.local/share/jupyter

# Switch back to jovyan to avoid accidental container runs as root
USER jovyan
WORKDIR $HOME

# conda sirve para instalar dependencias
# pip install sirve para installar en el sistema el command de installacion beakerx_kernel_groovy
# pero las dependencias de libraries de java pueden copiarse sin necesitar gradlew
# Por cierto revisa que rungradle ya no funciona, te lo has cargado xD